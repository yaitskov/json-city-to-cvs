package com.github.dan.city;

import com.google.gson.annotations.SerializedName;

/**
 * Daneel Yaitskov
 */
public class AirPort {
    @SerializedName("_id")
    private final String id;
    private final String name;
    private final String type;
    @SerializedName("geo_position")
    private final GeoPosition getPosition;

    public AirPort(String id, String name, String type, GeoPosition getPosition) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.getPosition = getPosition;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public GeoPosition getGetPosition() {
        return getPosition;
    }
}
