package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.AbstractPageTest;
import com.drainshawty.lab3.TestConfig;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProfileUITest extends AbstractPageTest {

    void goToProfilePage(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByHeaderButton(TestConfig.USERNAME, TestConfig.PASSWORD);
        mainPage.goToProfile();
    }

    String getAbsolutePath(String fileName) throws URISyntaxException {
        return new File(getClass().getClassLoader().getResource(fileName).toURI()).getAbsolutePath();
    }
    @TestTemplate
    void imageChangeTest(WebDriver driver) throws URISyntaxException, InterruptedException {
        goToProfilePage(driver);
        ProfileUI profilePage = new ProfileUI(driver);
        profilePage.uploadPhoto(getAbsolutePath("before.jpg"));
        Thread.sleep(2000);
        String beforeSrc = profilePage.getImgElement().getAttribute("src");
        profilePage.uploadPhoto(getAbsolutePath("after.jpg"));
        Thread.sleep(2000);
        assertNotEquals(beforeSrc, profilePage.getImgElement().getAttribute("src"), "Фотография не поменялась");
    }

    @TestTemplate
    void usernameChangeTest(WebDriver driver) throws InterruptedException {
        String tempName = "VIPERR";
        goToProfilePage(driver);
        ProfileUI profilePage = new ProfileUI(driver);
        profilePage.changeUsername(tempName);
        Thread.sleep(2000);
        assertEquals(tempName, profilePage.getUsernameHeader().getText(), "Имя не удалось поменять");
        profilePage.changeUsername(TestConfig.USERNAME);
        Thread.sleep(2000);
        assertEquals(TestConfig.USERNAME, profilePage.getUsernameHeader().getText(), "Имя не удалось вернуть");
    }
}
