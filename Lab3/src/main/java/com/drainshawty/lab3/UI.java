package com.drainshawty.lab3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UI {

    protected final WebDriver driver;

    public UI(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
