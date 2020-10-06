package com.experthub.smartlock.models;

import android.graphics.drawable.Drawable;

public class App {
    private Drawable icon;
    private String packageName;
    private String name;

    public App(Drawable icon, String packageName, String name) {
        this.icon = icon;
        this.packageName = packageName;
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
