package com.github.dan.city;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class GoEuroApiImpl implements GoEuroApi {
    private final Charset cs;
    private final Transport cityTransport;

    public GoEuroApiImpl(Transport cityTransport, Charset cs) {
        this.cityTransport = cityTransport;
        this.cs = cs;
    }

    public List<AirPort> portsByCity(String cityName) {
        try {
            try (InputStream is = cityTransport.call(cityName)) {

                return Arrays.asList(new GsonBuilder().create().fromJson(
                        new InputStreamReader(is, cs),
                        AirPort[].class));
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new RuntimeIo(e);
        }
    }
}
