package com.drainshawty.lab1.task3;

import java.util.Objects;

public class Location {
    String name;
    double x, y;

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[%s on x: %.1f, y: %.1f]", name, x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(x, location.x) == 0 && Double.compare(y, location.y) == 0 && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, x, y);
    }
}
