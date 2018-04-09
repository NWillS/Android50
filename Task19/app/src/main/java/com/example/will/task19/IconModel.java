package com.example.will.task19;

import android.graphics.drawable.Drawable;

@SuppressWarnings({"CanBeFinal", "FieldMayBeFinal", "UnnecessaryThis"})
class IconModel {
    private Drawable drawable;
    private String text;

    IconModel(Drawable drawable, String text) {
        this.drawable = drawable;
        this.text = text;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public String getText() {
        return this.text;
    }
}
