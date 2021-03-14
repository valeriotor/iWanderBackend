package com.valeriotor.iWanderBackend.util;

import com.google.gson.Gson;

public class GSONUtil {

    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
}
