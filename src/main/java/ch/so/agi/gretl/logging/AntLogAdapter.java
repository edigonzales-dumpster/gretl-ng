package ch.so.agi.gretl.logging;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Class taking care of the logging when using the Steps integrated in Ant
 * (When running the corresponding Tasks in Ant).
 */
public class AntLogAdapter implements GretlLogger {
    Task task;
    boolean taskLogger = false;
    
    AntLogAdapter(Object obj) {
        if (obj instanceof Task) {
            this.task = (Task) obj;
            //this.project = task.getProject();
        }
              
    }

    public void info(String msg) {
        task.log(msg, Project.MSG_INFO);
    }

    public void debug(String msg) {
        task.log(msg, Project.MSG_DEBUG);
    }

    public void verbose(String msg) {
        task.log(msg, Project.MSG_VERBOSE);
    }

    public void error(String msg, Throwable thrown) {
        task.log(msg, Project.MSG_ERR);
    }
}
