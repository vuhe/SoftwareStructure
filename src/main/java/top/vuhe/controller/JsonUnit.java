package top.vuhe.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author vuhe
 */
public class JsonUnit {
    private static final Gson JSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();

    public static String toJson(Object obj) {
        return JSON.toJson(obj);
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        return JSON.fromJson(str, clazz);
    }
}
