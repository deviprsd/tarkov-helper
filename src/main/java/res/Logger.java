package res;

import java.io.PrintStream;

public class Logger {
    private static int logLevel = -1;

    public static final int OFF = -1;
    public static final int INFO = 0;
    public static final int WARN = 1;
    public static final int CRIT = 2;
    public static final int ERR = 3;
    public static final int ALL = 4;

    public static void setLogLevel(int level) {
        if(level <= 4 && level >= -1) {
            logLevel = level;
        }
    }

    private static void log(Object msg, int level, PrintStream out) {
        if(level != OFF && level <= logLevel) {
            out.println(msg);
        }
    }

    public static void info(Object msg) {
        log(msg, INFO, System.out);
    }

    public static void warn(Object msg) {
        log(msg, WARN, System.out);
    }

    public static void crit(Object msg) {
        log(msg, CRIT, System.out);
    }

    public static void err(Object msg) {
        log(msg, ERR, System.err);
    }
}
