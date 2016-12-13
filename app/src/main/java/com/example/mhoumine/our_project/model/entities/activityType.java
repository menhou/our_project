package com.example.mhoumine.our_project.model.entities;

/**
 * Created by Gal on 06/12/2016.
 */

public enum activityType {
    HOTEL_RESORT("Hotel Resort"),
    TRIP("Trip"),
    ENTERTAINMENT_SHOW("Entertainment Show"),
    FLIGHT("Flight");

    private final String name;

    private activityType(String s) {
        name = s;
    }


    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    @Override
    public String toString() {
        return name;
    }

    public static activityType getEnum(String code) {

        switch (code) {
            case "Hotel Resort":
                return activityType.HOTEL_RESORT;
            case "Trip":
                return activityType.TRIP;
            case "Entertainment Show":
                return activityType.ENTERTAINMENT_SHOW;
            case "Flight":
                return activityType.FLIGHT;
            default:
                return null;
        }
    }
}

