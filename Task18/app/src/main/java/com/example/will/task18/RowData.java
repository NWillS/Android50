package com.example.will.task18;

import android.graphics.drawable.Drawable;

class RowData {
    private Drawable image;
    private String detail;

    public RowData(Drawable image, String detail) {
        this.image = image;
        this.detail = detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDetail() {
        return this.detail;
    }

    public Drawable getImage() {
        return this.image;
    }
}
