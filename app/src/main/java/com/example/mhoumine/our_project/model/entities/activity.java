package com.example.mhoumine.our_project.model.entities;

import java.util.Date;
/**
 * Created by mhoumine on 27/11/2016.
 */

public class activity {
    private activityType activityInfo;
    private String country;
    private Date startDate;
    private Date endDate;
    private double cost;
    private String description;
    private  String id;

    public activity() {
    }

    public activity(activityType activityInfo, String country, Date startDate, Date endDate, double cost, String description, String id) {
        this.activityInfo = activityInfo;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.description = description;
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public activityType getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(activityType activityInfo) {
        this.activityInfo = activityInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
