package com.drainshawty.lab1.task3;

public class Bee extends Animal {

    public Bee(boolean awareness, Location location) {
        this.awareness = awareness;
        this.location = location;
    }
    @Override
    public String getName() {
        return "Bee";
    }

    @Override
    public boolean isAware() {
        return awareness;
    }

    @Override
    public String sayHello() {
        return "Bjjjjj";
    }


}
