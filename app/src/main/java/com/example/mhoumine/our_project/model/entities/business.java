package com.example.mhoumine.our_project.model.entities;

/**
 * Created by mhoumine on 27/11/2016.
 */

public class business {
    private  String id;
    private  String name;
    private String country;
    private  String city;
    private String street;
    private String phoneNumber;
    private  String email;
    private String linkUrl;

    public business() {

    }

    public business(String id, String name, String country, String city, String phoneNumber, String street, String email, String linkUrl) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.email = email;
        this.linkUrl = linkUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
