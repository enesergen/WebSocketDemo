package com.enesergen.havelsan.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Model implements Serializable {

    private int id;
    private int shipId;
    private int speed;
    private int angle;
    private double []coordinates;
    private Date time;

    public Model(int id, int shipId, int speed, int angle, double[] coordinates, Date time) {
        this.id = id;
        this.shipId = shipId;
        this.speed = speed;
        this.angle = angle;
        this.coordinates = coordinates;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", shipId=" + shipId +
                ", speed=" + speed +
                ", angle=" + angle +
                ", coordinates=" + Arrays.toString(coordinates) +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
