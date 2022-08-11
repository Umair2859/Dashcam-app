package com.example.dashcam.model;


import java.util.jar.Attributes;

public class snaps {
    String Name;
    String uri;
    String date;
    String size;

    public snaps() {
        this.Name = Name;
        this.uri = uri;
        this.date = date;
        this.size = size;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}