package com.example.game;

public class Cat {

    private int width = 60, weight = 50;
    private boolean takeMouse = false;
    private int speed = 300;

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isTakeMouse() {
        return takeMouse;
    }

    public void setTakeMouse(boolean takeMouse) {
        this.takeMouse = takeMouse;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
