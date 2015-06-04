package com.github.dan.city;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Daneel Yaitskov
 */
public class AirPortCsvDao {
    private final CSVFormat format;

    public AirPortCsvDao(CSVFormat format) {
        this.format = format;
    }

    public void print(OutputStreamWriter out, Iterable<AirPort> ports) {
        try {
            CSVPrinter printer = new CSVPrinter(out, format);
            for (AirPort port : ports) {
                printer.printRecord(port.getId(), port.getName(),
                        port.getType(),
                        port.getGetPosition().getLatitude(),
                        port.getGetPosition().getLongitude());
            }
        } catch (IOException e) {
            throw new RuntimeIo(e);
        }
    }
}
