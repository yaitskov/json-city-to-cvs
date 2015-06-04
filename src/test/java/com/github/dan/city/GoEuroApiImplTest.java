package com.github.dan.city;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Daneel Yaitskov
 */
public class GoEuroApiImplTest {
    private static final String CITY = "city";

    @Test
    public void getCityPorts() {
        GoEuroApiImpl api = new GoEuroApiImpl(new Transport() {
            public InputStream call(String arg) {
                Assert.assertEquals(CITY, arg);
                return getClass().getResourceAsStream("/city-ports.json");
            }
        }, Charset.forName("UTF-8"));

        ReflectionAssert.assertLenientEquals(
                Arrays.asList(new AirPort("376217", "Berlin", "location",
                        new GeoPosition(52.52437, 13.41053))),
                api.portsByCity(CITY));
    }
}
