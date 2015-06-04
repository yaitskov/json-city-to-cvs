package com.github.dan.city;

import java.io.InputStream;

/**
 * Daneel Yaitskov
 */
public interface Transport {
    InputStream call(String arg);
}
