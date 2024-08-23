package utility;

import java.io.IOException;
import java.util.logging.*;

public class GlobalLogger extends FileHandler {
    private static final Logger GLOBAL_LOGGER = Logger.getLogger(GlobalLogger.class.getName());
    private static FileHandler fileHandler;
    private static final String LOG_FILE_PATH = "C:\\Users\\halo3\\Desktop\\Log.log";

    static {
        try {
            if (fileHandler != null) {
                fileHandler.close(); // Ensure any existing handler is closed
            }

            fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            GLOBAL_LOGGER.setUseParentHandlers(false);
            GLOBAL_LOGGER.addHandler(fileHandler);
        } catch (IOException ioException) {
            System.err.println("\nCouldn't log error: " + ioException.getCause());
        }
    }

    //Create a new object if you want to log files to different file
    public GlobalLogger(String pattern, boolean append) throws IOException, SecurityException {
        fileHandler = new FileHandler(pattern, append);
    }

    //Set error level and append it to a file
    public static void logExceptionsInFile(String warningLevel, String errorMessage, Exception e) {
        GLOBAL_LOGGER.log(Level.parse(warningLevel), errorMessage, e);
    }

    public static void logInfoInFile(String warningLevel, String infoMessage) {
        GLOBAL_LOGGER.log(Level.parse(warningLevel), infoMessage);
    }
}