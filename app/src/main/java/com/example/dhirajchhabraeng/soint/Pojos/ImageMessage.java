package com.example.dhirajchhabraeng.soint.Pojos;

public class ImageMessage {

    private String imageUrl;
    private int horizontalPosition;

    public ImageMessage(String imageUrl, int horizontalPosition) {
        this.imageUrl = imageUrl;
        this.horizontalPosition = horizontalPosition;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }
}
