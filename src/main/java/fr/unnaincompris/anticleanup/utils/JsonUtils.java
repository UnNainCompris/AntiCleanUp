package fr.unnaincompris.anticleanup.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.File;

public class JsonUtils {
    @Getter private static final Gson gson =
        new GsonBuilder()
        .setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .create();

    public static <T> T getData(File file, Class<T> type) {
        return gson.fromJson(FileUtils.readWholeFile(file), type);
    }


    public static void writeData(File file, Object clazz) {
        FileUtils.writeFile(file, gson.toJson(clazz));
    }

    public static File getOrCreateJson(File parent, String fileName) {
        return FileUtils.getOrCreateFile(parent, fileName + ".json");
    }

    public static boolean deleteJson(File parent, String fileName) {
        return FileUtils.deleteFile(parent, fileName + ".json");
    }
}
