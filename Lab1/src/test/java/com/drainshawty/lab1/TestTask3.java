package com.drainshawty.lab1;

import com.drainshawty.lab1.task3.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTask3 {

    @Test
    public void whaleInstantiationTest() {
        Location location = new Location("Sea", 0, 0);
        Animal whale = new Whale(false, location);
        assertAll(
                () -> assertEquals(whale.getName(), "Whale"),
                () -> assertEquals(whale.sayHello(), "Woooooooo"),
                () -> assertEquals(whale.getLocation(), new Location("Sea", 0, 0)),
                () -> assertFalse(whale.isAware()),
                () -> {
                    whale.changeAwareness();
                    assertTrue(whale.isAware());
                }
        );
    }

    @Test
    public void beeInstantiationTest() {
        Location location = new Location("Air", 0, 0);
        Animal bee = new Bee(false, location);
        assertAll(
                () -> assertEquals(bee.getName(), "Bee"),
                () -> assertEquals(bee.sayHello(), "Bjjjjj"),
                () -> assertEquals(bee.getLocation(), new Location("Air", 0, 0)),
                () -> assertFalse(bee.isAware()),
                () -> {
                    bee.changeAwareness();
                    assertTrue(bee.isAware());
                }
        );
    }

    @Test
    public void timerInstantiationTest() {
        Timer timer = new Timer();
        assertAll(
                () -> assertEquals(timer.getDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now())),
                () -> assertFalse(timer.isTimeToChange()),
                () -> {
                    timer.decision();
                    assertEquals(timer.getDate(),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now().plusDays(1)));
                }
        );
    }

    @Test
    public void locationInstantiationTest() {
        Location location = new Location("Sosnovka military base", 0, 0);
        assertAll(
                () -> assertEquals(location.toString(), "[Sosnovka military base on x: 0,0, y: 0,0]"),
                () -> {
                    location.moveTo(1.1, 1.1);
                    assertEquals(location.toString(), "[Sosnovka military base on x: 1,1, y: 1,1]");
                }
        );
    }
}
