package ch.so.agi.gretl.logging;

public interface GretlLogger {

    public void info(String msg);

    public void debug(String msg);

    public void error(String msg, Throwable thrown);

    public void verbose(String msg);
}
