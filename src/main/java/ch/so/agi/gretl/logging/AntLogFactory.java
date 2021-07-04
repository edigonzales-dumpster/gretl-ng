package ch.so.agi.gretl.logging;

public class AntLogFactory implements LogFactory {

    AntLogFactory() {}

    public GretlLogger getLogger(Class logSource) {
        return new AntLogAdaptor(logSource);
    }
}
