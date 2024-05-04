package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.AbstractPageTest;
import com.drainshawty.lab3.TestConfig;
import org.junit.jupiter.api.TestTemplate;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainUITest extends AbstractPageTest {

    List<String> queries = List.of(
            "how to find one fifth of 100",
            "the valence of gold",
            "Who was the serial killer from mishawaka Indiana?"
    );

    @TestTemplate
    void testCorrectLogin(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, TestConfig.PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(visibilityOf(mainPage.getAfterLogInImg()));
        assertTrue(mainPage.checkIfLogIn(TestConfig.USERNAME));
    }

    @TestTemplate
    void testIncorrectLogin(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, "FORBIDDEN");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        assertThrows(TimeoutException.class, () -> wait.until(visibilityOf(mainPage.getAfterLogInImg())));
    }

    @TestTemplate
    void testCorrectLoginByHeaderButton(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByHeaderButton(TestConfig.USERNAME, TestConfig.PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(visibilityOf(mainPage.getAfterLogInImg()));
        assertTrue(mainPage.checkIfLogIn(TestConfig.USERNAME));
    }

    @TestTemplate
    void testLogout(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, TestConfig.PASSWORD);
        mainPage.logout();
        assertFalse(mainPage.checkIfLogIn(TestConfig.USERNAME));
    }

    @TestTemplate
    void testSearch(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        for (String query : queries) {
            mainPage.trySearch(query);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getBestAnswer())));
        }
    }

    @TestTemplate
    void checkCategories(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        assertFalse(mainPage.getCategoriesLinks().isEmpty());
        mainPage.getCategoriesLinks().get(0).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getSubCategories())));
        assertFalse(mainPage.getSubCategoriesLinks().isEmpty());
        mainPage.getSubCategoriesLinks().get(0).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getQuestions())));
        assertTrue(mainPage.getQuestionsLinks().size() > 1);
        mainPage.getQuestionsLinks().get(1).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getTopAnswer())));
    }

    @TestTemplate
    void commentWithoutLogIn(WebDriver driver) {
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        assertFalse(mainPage.getCategoriesLinks().isEmpty());
        mainPage.getCategoriesLinks().get(0).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getSubCategories())));
        assertFalse(mainPage.getSubCategoriesLinks().isEmpty());
        mainPage.getSubCategoriesLinks().get(0).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getQuestions())));
        assertTrue(mainPage.getQuestionsLinks().size() > 1);
        mainPage.getQuestionsLinks().get(1).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getTopAnswer())));
        mainPage.createComment("thx");
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getErrorSpan())));
    }

    @TestTemplate
    void commentWithLogIn(WebDriver driver) throws InterruptedException {
        String msg = "thx";
        MainUI mainPage = new MainUI(driver);
        initDriver(TestConfig.BASE_URI, driver);
        mainPage.fullLogInByLink(TestConfig.USERNAME, TestConfig.PASSWORD);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        Thread.sleep(1000);
        assertFalse(mainPage.getCategoriesLinks().isEmpty());
        mainPage.getCategoriesLinks().get(0).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getSubCategories())));
        assertFalse(mainPage.getSubCategoriesLinks().isEmpty());
        mainPage.getSubCategoriesLinks().get(0).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getQuestions())));
        assertTrue(mainPage.getQuestionsLinks().size() > 1);
        mainPage.getQuestionsLinks().get(1).click();
        assertDoesNotThrow(() -> wait.until(visibilityOf(mainPage.getTopAnswer())));
        //if (mainPage.getMoreComments().isDisplayed()) mainPage.extendComments();
        int commentsAmount = mainPage.getComments().size();
        mainPage.createComment(msg);
        Thread.sleep(1000);
        assertEquals(commentsAmount + 1, mainPage.getComments().size());
        assertEquals(msg, mainPage.getCommentText(mainPage.getComments().get(mainPage.getComments().size()-1)));
    }
}
