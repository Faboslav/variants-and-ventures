package accessconverter;

import accessconverter.conversion.ATConverter;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class AccessConverterPlugin implements Plugin<Project>
{
	@Override
	public void apply(Project oldProject) {
		oldProject.getExtensions().create("accessConverter", AccessConverterExtension.class);

		oldProject.afterEvaluate(project -> {
			final AccessConverterExtension extension = project.getExtensions().getByType(AccessConverterExtension.class);
			final AccessConverterExtension.ConvertToATExtension atExtension = extension.getATExtension();

			if (atExtension != null) {
				if (ATConverter.convertAW(project.getLogger(), extension.getMcVersion(), atExtension.getAwLocation(), atExtension.getOutputLocation(), atExtension.doSortInput())) {
					project.getLogger().error("Access Widener Conversion into Access Transformer Finished Succesfully.");
				} else project.getLogger().error("[ERROR] Access Widener Conversion Failed");

			}
		});

	}
}