package org.project;

public class Station {
    private int station_id;
    private String station_name;

    public int getStation_id() {
        return station_id;
    }

    @Override
    public String toString() {
        return String.format("{Station_ID:%-5d\tStation_Name:%5s}",this.station_id,this.station_name);
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public Station(int station_id, String station_name) {
        this.station_id = station_id;
        this.station_name = station_name;
    }
}
