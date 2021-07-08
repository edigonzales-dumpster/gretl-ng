package ch.so.agi.gretl.logging;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/**
 * Holds the global logging factory used by the Step and helper classes to get a
 * logger instance. Holds either a logging factory for standalone use of the
 * steps classes as in unit tests or the Ant * logging environment for
 * integrated use of the steps in Ant.
 */
public class LogEnvironment {

    private static LogFactory currentLogFactory = null;
    
    private static Project project;

    public static void setLogFactory(LogFactory factory) {
        currentLogFactory = factory;
    }

    public static void initGradleIntegrated() {
        if (currentLogFactory == null) {
            setLogFactory(new AntLogFactory());
        }
    }

    public static void initStandalone() {
        initStandalone(Level.DEBUG);
    }

    public static void initStandalone(Level logLevel) {
        if (currentLogFactory == null) {
            setLogFactory(new CoreJavaLogFactory(logLevel));
        }
    }
    
    public static GretlLogger getLogger(Object obj) {
        System.out.println("çççç***"+obj.getClass());
        


        if (currentLogFactory == null) {
            try {
                if (Class.forName("org.apache.tools.ant.Project") != null) {
                    setLogFactory(new AntLogFactory());
                    
                    // Factory muss zwingend immer zuerst von einem Task
                    // gesetzt werden. Dann wird auch das Project initialisiert
                    // und die statischen Methoden können loggen.
                    if (obj instanceof Task) {
                        project = ((Task) obj).getProject();
                        return currentLogFactory.getLogger(obj);
                    } else {
                        return currentLogFactory.getLogger(project);
                    }
                }
            } catch (ClassNotFoundException e) {
                // use java logging if no Ant in classpath
                setLogFactory(new CoreJavaLogFactory(Level.DEBUG));
                return currentLogFactory.getLogger(obj);
            }
        }
        if (obj == null)
            throw new IllegalArgumentException("The logSource must not be null");
        
        setLogFactory(new CoreJavaLogFactory(Level.DEBUG));
        return currentLogFactory.getLogger(obj);
    }
}
