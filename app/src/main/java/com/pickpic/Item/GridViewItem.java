package com.pickpic.Item;

import android.graphics.Bitmap;

public class GridViewItem {
    private Bitmap thumbnail;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

}
