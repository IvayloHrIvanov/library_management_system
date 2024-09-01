package utility;

import java.io.IOException;
import java.util.logging.*;

public class GlobalLogger extends FileHandler {
    private static final Logger GLOBAL_LOGGER = Logger.getLogger(GlobalLogger.class.getName());
    private static FileHandler fileHandler;
    private static final String LOG_FILE_PATH = "D:\\Log.log"; // Default path for the log file

    static {
        try {
            if (fileHandler != null) {
                fileHandler.close(); // Close any existing file handler to avoid multiple handlers writing to the same file
            }

            fileHandler = new FileHandler(LOG_FILE_PATH, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            GLOBAL_LOGGER.setUseParentHandlers(false); // Disable console logging
            GLOBAL_LOGGER.addHandler(fileHandler);
        } catch (IOException ioException) {
            System.err.println("\nCouldn't log error: " + ioException.getCause());
        }
    }

    // Constructor to create a new logger instance that writes to a different file
    public GlobalLogger(String filePath, boolean append) throws IOException {
        fileHandler = new FileHandler(filePath, append);
        GLOBAL_LOGGER.addHandler(fileHandler);
    }

    //Set error level and append it to a file
    public static void logExceptionInFile(String warningLevel, String errorMessage, Exception e) {
        GLOBAL_LOGGER.log(Level.parse(warningLevel), errorMessage, e);
    }

    public static void logEventInFile(String warningLevel, String infoMessage) {
        GLOBAL_LOGGER.log(Level.parse(warningLevel), infoMessage);
    }
}