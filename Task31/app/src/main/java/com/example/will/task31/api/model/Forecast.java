package com.example.will.task31.api.model;

public class Forecast {
    private String dateLabel;
    private String telop;
    private String date;
    private Image image;

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public void setTelop(String telop) {
        this.telop = telop;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getDateLabel() {
        return this.dateLabel;
    }

    public String getTelop() {
        return this.telop;
    }

    public String getDate() {
        return this.date;
    }

    public Image getImage() {
        return this.image;
    }
}
