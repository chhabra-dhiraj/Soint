package com.example.dhirajchhabraeng.soint.Pojos;

public class TextMessage {

    private String message;
    private int horizontalPosition;

    public TextMessage(String message, int horizontalPosition) {
        this.message = message;
        this.horizontalPosition = horizontalPosition;
    }

    public String getMessage() {
        return message;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }
}
