package br.nthing.delivery;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {

    public Double latitude;
    public Double longitude;

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Location current() {
        return  new Location(-23.5505, -46.6333);
    }
}
