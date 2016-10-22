package com.unlp.tesis.steer.Model;

/**
 * Created by pedro on 18/10/16.
 */

public class PaidParkingArea {

    private float radius;
    private float Latitude;
    private float Longitude;

    public PaidParkingArea() {
    }

    public PaidParkingArea(float radius, float latitude, float longitude) {
        this.radius = radius;
        Latitude = latitude;
        Longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }
}
