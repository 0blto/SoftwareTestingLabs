package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.AbstractPageTest;
import com.drainshawty.lab3.TestConfig;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPageTest extends AbstractPageTest {
    @TestTemplate
    void testCorrectLogin(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, TestConfig.PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(visibilityOf(mainPage.getAfterLogInImg()));
        assertTrue(mainPage.checkIfLogIn(TestConfig.USERNAME));
    }

    @TestTemplate
    void testIncorrectLogin(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, "DETACHED");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        assertThrows(TimeoutException.class, () -> wait.until(visibilityOf(mainPage.getAfterLogInImg())));
    }

    @TestTemplate
    void testLogout(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, TestConfig.PASSWORD);
        mainPage.logout();
        assertFalse(mainPage.checkIfLogIn(TestConfig.USERNAME));
    }
}
