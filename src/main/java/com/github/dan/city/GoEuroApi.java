package com.github.dan.city;

import java.util.List;

/**
 * Daneel Yaitskov
 */
public interface GoEuroApi {
    List<AirPort> portsByCity(String cityName);
}
