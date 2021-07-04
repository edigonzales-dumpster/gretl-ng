package ch.so.agi.gretl.logging;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Class taking care of the logging when using the Steps integrated in Ant
 * (When running the corresponding Tasks in Ant).
 */
public class AntLogAdaptor extends Task implements GretlLogger {

    AntLogAdaptor(Class logSource) {}

    public void info(String msg) {
        log(msg, Project.MSG_INFO);
    }

    public void debug(String msg) {
        log(msg, Project.MSG_DEBUG);
    }

    public void verbose(String msg) {
        log(msg, Project.MSG_VERBOSE);
    }

    public void error(String msg, Throwable thrown) {
        log(msg, Project.MSG_ERR);
    }
    
//    public void execute() {
//        System.out.println("**** 3");
//    }
}
