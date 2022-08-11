package com.example.dashcam.model;

public class videos {

    String Name;
    String uri;
    String Size;
    String Date;

    public videos() {
        this.Name = Name;
        this.uri = uri;
        this.Size =Size;
        this.Date =Date;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSize(int file_size) {
        return Size;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
