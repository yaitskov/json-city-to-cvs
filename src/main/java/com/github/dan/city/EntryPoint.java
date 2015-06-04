package com.github.dan.city;

import org.apache.commons.csv.CSVFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Daneel Yaitskov
 */
public class EntryPoint {
    private static final String HOST = "http://api.goeuro.com/";
    private static final String VERSION = "api/v2/position/suggest/en/";
    private static final Charset CS = Charset.forName("UTF-8");
    private static final CSVFormat OUT_FORMAT = CSVFormat.DEFAULT;

    private static final Logger logger = LoggerFactory.getLogger(EntryPoint.class);

    public static void main(String[] args) {
        final GoEuroApi api = new GoEuroApiImpl(new TransportImpl(HOST, VERSION), CS);
        final AirPortCsvDao csvDao = new AirPortCsvDao(OUT_FORMAT);
        int citiesGot = 0;
        for (String cityName : args) {
            logger.debug("Loading city [{}].", cityName);
            try {
                final List<AirPort> ports = api.portsByCity(cityName);
                try (final OutputStreamWriter out = openOutFile(cityName)) {
                    csvDao.print(out, ports);
                    ++citiesGot;
                }
            } catch (RuntimeIo | IOException e) {
                logger.error("Failed to get city [{}].", cityName, e);
            }
        }
        System.exit(Math.min(1, citiesGot));
    }

    private static OutputStreamWriter openOutFile(String cityName)
            throws IOException {
        return new OutputStreamWriter(
                Files.newOutputStream(Paths.get(cityName + ".csv"),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING),
                CS);
    }
}
