package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = LOG_DIR + "/app.log";
    private static final Logger logger = Logger.getLogger("AppLogger");

    static {
        try {
            Path logDirPath = Path.of(LOG_DIR);
            if (!Files.exists(logDirPath)) {
                Files.createDirectory(logDirPath);
            }

            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.setUseParentHandlers(false); // evitar consola duplicada
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Todas las clases usarán el mismo logger
    public static Logger getLogger() {
        return logger;
    }
}
