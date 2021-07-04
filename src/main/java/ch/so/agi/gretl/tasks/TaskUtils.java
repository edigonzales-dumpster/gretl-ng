package ch.so.agi.gretl.tasks;

import java.io.File;
import java.nio.file.Paths;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import ch.so.agi.gretl.util.GretlException;

public class TaskUtils {
    public static File getFilePath(Project project, String fileName) {
        File fileObj = new File(fileName);
        if (fileObj.isAbsolute()) {
            return fileObj;
        }
        return Paths.get(project.getBaseDir().getAbsolutePath(), new File(fileName).getPath()).toFile();
    } 
    
    /**
     * Used to Convert a thrown Exception into a BuildException. BuildException
     * must be thrown to halt the Execution of the Ant build.
     *
     * BuildException instances are converted, all other Exceptions are wrapped
     * (nested)
     */
    public static BuildException toAntBuildException(Exception ex) {
        BuildException res = null;

        String exClassName = ex.getClass().toString();
        String gretlClassName = GretlException.class.toString();

        if (exClassName.equals(gretlClassName)) { // can't use instanceof as must return false for GretlException
                                                  // subclasses.
            res = new BuildException(ex.getMessage());
        } else {
            res = new BuildException("Inner Exception Message: " + ex.getMessage(), ex);
        }
        return res;
    }
}
