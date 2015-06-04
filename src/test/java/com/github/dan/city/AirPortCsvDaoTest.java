package com.github.dan.city;

import org.apache.commons.csv.CSVFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * Daneel Yaitskov
 */
public class AirPortCsvDaoTest {

    private static final String ID = "id";
    private static final String NAME = "na, me";
    private static final String TYPE = "type";
    private static final double LATITUDE = 12.12;
    private static final double LONGITUDE = 77.11;

    @Test
    public void print() throws IOException {
        AirPortCsvDao dao = new AirPortCsvDao(CSVFormat.DEFAULT);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        dao.print(writer,
                Arrays.asList(new AirPort(ID, NAME, TYPE,
                        new GeoPosition(LATITUDE, LONGITUDE))));
        writer.close();
        Assert.assertEquals(ID + ",\""
                + NAME + "\","
                + TYPE + ","
                + LATITUDE + ","
                + LONGITUDE + "\r\n",
                out.toString());
    }
}
