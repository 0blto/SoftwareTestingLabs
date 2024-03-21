package com.drainshawty.lab1.task3;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class Timer {
    private boolean moment;
    private LocalDateTime date;

    public Timer() {
        this.moment = false;
        this.date = LocalDateTime.now();
    }

    public boolean isTimeToChange() {
        return moment;
    }

    public void decision() {
        this.date = this.date.plusDays(1);
        if (Math.random() > 0.8) moment = true;
    }

    public String getDate() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
    }
}
