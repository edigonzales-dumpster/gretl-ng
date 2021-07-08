package ch.so.agi.gretl.logging;

public class CoreJavaLogFactory implements LogFactory {

    private Level globalLogLevel;

    CoreJavaLogFactory(Level globalLogLevel) {
        this.globalLogLevel = globalLogLevel;
    }

//    @Override
//    public GretlLogger getLogger(Class logSource) {
//        return new CoreJavaLogAdaptor(logSource, globalLogLevel);
//    }

    @Override
    public GretlLogger getLogger(Object obj) {
        Class clazz = (Class) obj.getClass();
        return new CoreJavaLogAdapter(clazz, globalLogLevel);
    }
}