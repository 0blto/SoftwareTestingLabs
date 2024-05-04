package com.drainshawty.lab3;

import io.github.bonigarcia.seljup.BrowserBuilder;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class AbstractPageTest {

    @RegisterExtension
    static final SeleniumJupiter SELENIUM_JUPITER = new SeleniumJupiter();

    @BeforeAll
    static void setup() {
        SELENIUM_JUPITER.addBrowsers(BrowserBuilder.chrome().build());
    }

    public static void initDriver(String url, WebDriver driver) {
        driver.get(url);
        if (driver instanceof ChromeDriver) driver.manage().window().setSize(new Dimension(1200, 1000));
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3))
                .pageLoadTimeout(Duration.ofSeconds(10))
                .scriptTimeout(Duration.ofSeconds(10));
    }
}
