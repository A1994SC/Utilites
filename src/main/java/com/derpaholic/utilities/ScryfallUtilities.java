package com.derpaholic.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ScryfallUtilities {

    private static final String url = "https://api.scryfall.com/";
    private static final String set = url + "/sets/%s";

    private static JsonObject getJsonFromURL(String url) {
        try {
            System.out.println(url);
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
        try {
            return getJsonFromURL(String.format(set, setid)).get("name").getAsString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Used as a generic method to call Scryfall api methods. The args are encoded to be url safe.
     *
     * @param type Example is 'cards/search?q=%s' or 'sets/%s'
     * @param args Example is 'Flash' or 'AER"
     * @return the scryfall json object
     */
    public static JsonObject getFromURL(String type, String args) {
        try {
            return getJsonFromURL(String.format((url + type), URLEncoder.encode(args, "UTF-8")));
        } catch (Exception e) {
            return null;
        }
    }
}
