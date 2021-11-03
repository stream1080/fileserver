package com.example.client.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionUtil {

    public static HttpURLConnection Util(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Connection","Keep-Alive");
        connection.setRequestProperty("Charset","UTF-8");

        return connection;
    }

}
