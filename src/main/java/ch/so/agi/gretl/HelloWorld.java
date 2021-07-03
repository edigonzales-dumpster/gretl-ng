package ch.so.agi.gretl;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

public class HelloWorld extends Task {
    
    String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void execute() {
        System.out.println("Hello World Fubar hhh");            

        // use of the reference to Project-instance
        //String message = getProject().getProperty("ant.project.name");

        // Task's log method
        log("Here is project '" + message + "'.", Project.MSG_INFO);

        // where this task is used?
        log("I am used in: " +  getLocation() );
        

        System.out.println();

//        DefaultLogger logger = (DefaultLogger) getProject().getBuildListeners().get(0);
        
        
    }
}
