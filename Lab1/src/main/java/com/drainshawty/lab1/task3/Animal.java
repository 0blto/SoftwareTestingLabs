package com.drainshawty.lab1.task3;

public abstract class Animal {
    boolean awareness;
    Location location;

    public Location getLocation() {
        return location;
    }
    public abstract String getName();

    public void changeAwareness() {this.awareness = !this.awareness;}

    public abstract boolean isAware();

    public abstract String sayHello();
}
