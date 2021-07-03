package ch.so.agi.gretl.tasks;

import java.io.File;
import java.nio.file.Paths;

import org.apache.tools.ant.Project;

public class TaskUtils {
    public static File getFilePath(Project project, String fileName) {
        File fileObj = new File(fileName);
        if (fileObj.isAbsolute()) {
            return fileObj;
        }
        return Paths.get(project.getBaseDir().getAbsolutePath(), new File(fileName).getPath()).toFile();
    } 
}
