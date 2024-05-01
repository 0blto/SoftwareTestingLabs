package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.AbstractPageTest;
import com.drainshawty.lab3.TestConfig;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URISyntaxException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class ProfilePageTest extends AbstractPageTest {

    void goToProfilePage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByHeaderButton(TestConfig.USERNAME, TestConfig.PASSWORD);
        mainPage.goToProfile();
    }

    String getAbsolutePath(String fileName) throws URISyntaxException {
        return new File(ProfilePage.class.getClassLoader().getResource(fileName).toURI()).getAbsolutePath();
    }
    @TestTemplate
    void imageChangeTest(WebDriver driver) throws URISyntaxException, InterruptedException {
        goToProfilePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.uploadPhoto(getAbsolutePath("before.jpg"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(visibilityOf(profilePage.getInfo()));
        Thread.sleep(1000);
        String beforeSrc = profilePage.getImgElement().getAttribute("src");
        profilePage.uploadPhoto(getAbsolutePath("after.jpg"));
        wait.until(visibilityOf(profilePage.getInfo()));
        Thread.sleep(1000);
        assertNotEquals(beforeSrc, profilePage.getImgElement().getAttribute("src"), "Фотография не поменялась");
    }

    @TestTemplate
    void usernameChangeTest(WebDriver driver) throws InterruptedException {
        String tempName = "VIPERR";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        goToProfilePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.changeUsername(tempName);
        wait.until(visibilityOf(profilePage.getInfo()));
        Thread.sleep(1000);
        assertEquals(tempName, profilePage.getUsernameHeader().getText(), "Имя не удалось поменять");
        profilePage.changeUsername(TestConfig.USERNAME);
        wait.until(visibilityOf(profilePage.getInfo()));
        Thread.sleep(1000);
        assertEquals(TestConfig.USERNAME, profilePage.getUsernameHeader().getText(), "Имя не удалось вернуть");
    }
}
