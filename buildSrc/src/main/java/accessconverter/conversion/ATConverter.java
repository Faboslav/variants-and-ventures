package accessconverter.conversion;

import org.slf4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ATConverter
{

	public static String convertFile(Logger logger, File awFileToConvert) {
		Map<String, ATEntry> map = new HashMap<>();
		String lineCopy = "";

		try (BufferedReader br = new BufferedReader(new FileReader(awFileToConvert))) {
			int counter = 0;
			String line;
			while ((line = br.readLine()) != null) {
				lineCopy = line.trim();
				if (lineCopy.isEmpty() || lineCopy.startsWith("#") || counter == 0) {
					counter++;
					continue;
				}

				String[] split = lineCopy.split(" ");
				if (split.length < 3) {
					counter++;
					continue; // malformed line
				}

				String modifier;
				switch (split[0]) {
					case "accessible" -> modifier = "public";
					case "extendable", "mutable" -> modifier = "public-f";
					default -> modifier = "public";
				}

				String type = split[1];
				String clazz = split[2].replace("/", ".");
				String key = clazz;

				ATEntry entry;

				if ("class".equals(type)) {
					entry = map.getOrDefault(key, new ATEntry(modifier, clazz, "", ""));
					if ("public-f".equals(modifier)) entry.setModifier("public-f");
					map.put(key, entry);
				} else if ("field".equals(type) && split.length >= 5) {
					String name = split[3];
					entry = map.getOrDefault(key + name, new ATEntry(modifier, clazz, name, ""));
					if ("public-f".equals(modifier)) entry.setModifier("public-f");
					map.put(key + name, entry);
				} else if ("method".equals(type) && split.length >= 5) {
					String name = split[3];
					String desc = split[4];
					entry = map.getOrDefault(key + name + desc, new ATEntry(modifier, clazz, name, desc));
					if ("public-f".equals(modifier)) entry.setModifier("public-f");
					map.put(key + name + desc, entry);
				}

				counter++;
			}
		} catch (Exception e) {
			logger.error("[ERROR] Failed to parse AW: " + e.getMessage());
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
			logger.error("[ERROR] Erroring line: " + lineCopy);
		}

		return map.values().stream()
			.map(ATEntry::toString)
			.sorted(String::compareToIgnoreCase)
			.collect(Collectors.joining(System.lineSeparator()));
	}

	public static boolean convertAW(
		Logger logger,
		String version,
		File awFileToConvert,
		File toOutputIn,
		boolean sortInput
	) {
		if (awFileToConvert == null) {
			logger.error("[ERROR] Supplied null Access Widener file path!");
			return false;
		}

		if (toOutputIn == null) {
			logger.error("[ERROR] Null output Access Transformer file provided!");
			return false;
		}

		String toWrite = convertFile(logger, awFileToConvert);

		try (FileWriter fileWriter = new FileWriter(toOutputIn)) {
			fileWriter.write(toWrite);
		} catch (IOException exception) {
			logger.error(exception.getMessage(), exception);
			return false;
		}

		if (sortInput) {
			try {
				List<String> originalFile = Files.readAllLines(awFileToConvert.getAbsoluteFile().toPath());
				originalFile.sort(String::compareToIgnoreCase);
				originalFile.removeIf(it -> it.equals("accessWidener v1 named") || it.equals("accessWidener v2 named"));
				try (FileWriter fileWriter = new FileWriter(awFileToConvert)) {
					fileWriter.write("accessWidener v2 named");
					fileWriter.write(System.lineSeparator());
					fileWriter.write(String.join(System.lineSeparator(), originalFile));
					logger.error("Successfully sorted input Access Widener File");
				}
			} catch (IOException exception) {
				logger.error(exception.getMessage(), exception);
			}
		}

		return true;
	}

	public static class ATEntry
	{
		public String modifier;
		public final String clazz;
		public final String name;
		public final String signature; // only for methods

		public ATEntry(String modifier, String clazz, String name, String signature) {
			this.modifier = modifier;
			this.clazz = clazz;
			this.name = name;
			this.signature = signature;
		}

		@Override
		public String toString() {
			if (signature.isEmpty()) {
				return modifier + " " + clazz + (name.isEmpty() ? "" : " " + name);
			} else {
				return modifier + " " + clazz + " " + name + signature;
			}
		}

		void setModifier(String modifier) {
			this.modifier = modifier;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof ATEntry atEntry)) return false;
			return Objects.equals(modifier, atEntry.modifier) &&
				   Objects.equals(clazz, atEntry.clazz) &&
				   Objects.equals(name, atEntry.name) &&
				   Objects.equals(signature, atEntry.signature);
		}

		@Override
		public int hashCode() {
			return Objects.hash(modifier, clazz, name, signature);
		}
	}
}