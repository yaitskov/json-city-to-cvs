package com.github.dan.city;

import java.io.IOException;

/**
 * Daneel Yaitskov
 */
public class RuntimeIo extends RuntimeException {
    public RuntimeIo(IOException cause) {
        super(cause);
    }
}
