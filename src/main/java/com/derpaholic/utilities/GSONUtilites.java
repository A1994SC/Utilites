package com.derpaholic.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONUtilites {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String prettyPrint(Object obj) {
        return gson.toJson(obj);
    }

}
