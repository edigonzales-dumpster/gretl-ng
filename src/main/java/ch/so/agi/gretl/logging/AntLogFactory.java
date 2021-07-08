package ch.so.agi.gretl.logging;

import org.apache.tools.ant.Task;

public class AntLogFactory implements LogFactory {

    public AntLogFactory() {}

    @Override
    public GretlLogger getLogger(Object obj) {
        return new AntLogAdapter(obj);
    }
}
