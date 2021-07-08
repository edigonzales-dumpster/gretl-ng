package ch.so.agi.gretl.logging;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Class taking care of the logging when using the Steps integrated in Ant
 * (When running the corresponding Tasks in Ant).
 */
public class AntLogAdapter implements GretlLogger {
    Task task;
    Project project;
    
    AntLogAdapter(Object obj) {
        if (obj instanceof Task) {
            this.task = (Task) obj;
        } else {
            this.project = (Project) obj;
        }
    }

    public void info(String msg) {
        if (task != null) {
            task.log(msg, Project.MSG_INFO);   
        } else {
            project.log(msg, Project.MSG_INFO);   
        }
    }

    public void debug(String msg) {
        if (task != null) {
            task.log(msg, Project.MSG_DEBUG);
        } else {
            project.log(msg, Project.MSG_DEBUG);
        }
    }

    public void verbose(String msg) {
        if (task != null) { 
            task.log(msg, Project.MSG_VERBOSE);
        } else {
            project.log(msg, Project.MSG_VERBOSE);
        }
    }

    public void error(String msg, Throwable thrown) {
        if (task != null) { 
            task.log(msg, Project.MSG_ERR);
        } else {
            project.log(msg, Project.MSG_ERR);
        }
    }
}
