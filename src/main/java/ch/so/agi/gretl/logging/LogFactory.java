package ch.so.agi.gretl.logging;

import org.apache.tools.ant.Project;

/**
 * Returns a Logger instance
 */
public interface LogFactory {
    public GretlLogger getLogger(Object obj);
}
