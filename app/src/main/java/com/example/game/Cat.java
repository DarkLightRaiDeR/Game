package com.example.game;

public class Cat {

    private boolean takeMouse = false;
    private float speed = 0.1f;

    float getSpeed() {
        this.speed += 0.005;
        return speed;
    }

    public int getWidth() {
        int width = 60;
        return width;
    }

    public int getHeight() {
        int height = 60;
        return height;
    }

    boolean isTakeMouse() {
        return takeMouse;
    }

    void setTakeMouse(boolean takeMouse) {
        this.takeMouse = takeMouse;
    }

}
