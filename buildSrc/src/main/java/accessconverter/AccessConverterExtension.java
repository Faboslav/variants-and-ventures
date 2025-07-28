package accessconverter;

import org.gradle.api.Action;

import java.io.File;

public class AccessConverterExtension
{
	private final ConvertToATExtension atExtension = new ConvertToATExtension();
	private String mcVersion;

	public ConvertToATExtension getATExtension() {
		return atExtension;
	}

	public void convertAW(Action<? super ConvertToATExtension> action) {
		action.execute(atExtension);
	}

	public String getMcVersion() {
		return mcVersion;
	}

	public void mcVersion(String mcVersion) {
		this.mcVersion = mcVersion;
	}

	public static class ConvertToATExtension
	{
		private File awLocation;
		private File outputLocation;
		private boolean sortInput;

		public File getAwLocation() {
			return awLocation;
		}

		public void fileToConvert(File file) {
			this.awLocation = file;
		}

		public File getOutputLocation() {
			return outputLocation;
		}

		public void fileOutput(File file) {
			this.outputLocation = file;
		}

		public void sortInput(boolean bool) {
			this.sortInput = bool;
		}

		public boolean doSortInput() {
			return this.sortInput;
		}
	}
}