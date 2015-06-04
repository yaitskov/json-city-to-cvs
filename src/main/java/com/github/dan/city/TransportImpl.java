package com.github.dan.city;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Daneel Yaitskov
 */
public class TransportImpl implements Transport {
    private final String baseUrl;

    public TransportImpl(String host, String api) {
        baseUrl = host + api;
    }

    public InputStream call(String arg) {
        try {
            final URL url = new URL(baseUrl + arg);
            final URLConnection connection = url.openConnection();
            return connection.getInputStream();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new RuntimeIo(e);
        }
    }
}
