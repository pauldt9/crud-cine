package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageService {

    public static String saveImage(File img, String fileName) {

        File folder = new File("moviesImages");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File path = new File(folder, fileName);

        try {
            Files.copy(img.toPath(), path.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return "moviesImages/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}