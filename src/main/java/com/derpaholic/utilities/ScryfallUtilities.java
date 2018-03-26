package com.derpaholic.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScryfallUtilities {

    private static final String url = "https://api.scryfall.com/";
    private static final String set = url + "/sets/%s";

    // https://github.com/A1994SC/MagicalList/blob/master/src/com/derpaholic/magic/misc/Utility.java
    private static JsonObject getJsonFromURL(String url) {
        try {
            HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

            return root.getAsJsonObject();
        } catch(Exception e) {
            return null;
        }
    }

    public static String getSetName(String setid) {
        JsonObject obj = getJsonFromURL(String.format(set, setid));

        return obj.get("name").getAsString();
    }


}