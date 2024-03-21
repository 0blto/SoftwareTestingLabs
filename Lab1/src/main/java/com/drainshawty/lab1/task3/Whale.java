package com.drainshawty.lab1.task3;

public class Whale extends Animal {

    public Whale(boolean awareness, Location location) {
        this.awareness = awareness;
        this.location = location;
    }

    @Override
    public String getName() {return "Whale";}

    @Override
    public boolean isAware() {return awareness;}

    @Override
    public String sayHello() {
        return "Woooooooo";
    }


}
