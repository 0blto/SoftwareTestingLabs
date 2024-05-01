package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.Page;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainPage extends Page {

    //USERSERV

    @FindBy(xpath = "//button[@class=\"h-8 m-2 p-2 text-white hover:bg-primaryLight body1 rounded flex items-center\"]")
    WebElement logInHeaderButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/main/section[1]/span/a[1]")
    WebElement logInByLink;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/div/button[4]")
    WebElement logInWithEmail;

    @FindBy(xpath = "//*[@id=\"email-input\"]")
    WebElement emailOrUsernameInput;

    @FindBy(xpath = "//*[@id=\"outlined-adornment-password\"]")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@id=\"loginUser\"]/button")
    WebElement submitLogInButton;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[4]/div[3]/span/div/div/div/button[3]/a")
    WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"profile-menu\"]/span/img")
    @Getter
    WebElement afterLogInImg;

    @FindBy(xpath = "/html/head/script[9]/text()")
    @Getter
    WebElement appConfig;

    //FUNCTIONALITY

    ////Searching
    @FindBy(xpath = "//*[@id=\"search-input\"]")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"best-answer\"]")
    @Getter
    WebElement bestAnswer;

    @FindBy(xpath = "//*[@id=\"top-answer\"]")
    @Getter
    WebElement topAnswer;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/main/div[2]")
    @Getter
    WebElement categories;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]")
    @Getter
    WebElement subCategories;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]")
    @Getter
    WebElement questions;

    @FindBy(xpath = "//*[@id=\"comment-input-box\"]")
    WebElement commentInput;

    @FindBy(xpath = "//*[@id=\"top-answer\"]/div[2]/span")
    @Getter
    WebElement errorSpan;

    @FindBy(xpath = "//*[@id=\"primaryBgPrimary\"]")
    WebElement moreComments;

    @FindBy(xpath = "//*[@id=\"top-answer\"]/div[2]/div[5]")
    WebElement comments;

    //Profile
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/div[4]/div[3]/span/div/div/div/button[1]/a")
    WebElement profile;

    public boolean checkIfLogIn(String username) {
        String flag = (String) ((JavascriptExecutor) driver).executeScript(
                "var scripts = document.querySelectorAll('script');" +
                        "for (var i = 0; i < scripts.length; i++) {" +
                        "    if (scripts[i].innerHTML.includes('" + username + "')) {" +
                        "        return 'POBEDA';" +
                        "    }" +
                        "}" +
                        "return 'PORAZHENIYE';"
        );
        return flag.equals("POBEDA");
    }

    public void fullLogInByLink(String email, String password) {
        logInByLink.click();
        logInWithEmail.click();
        emailOrUsernameInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitLogInButton.click();
    }

    public void fullLogInByHeaderButton(String email, String password) {
        logInHeaderButton.click();
        logInWithEmail.click();
        emailOrUsernameInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitLogInButton.click();
    }

    public void logout() {
        afterLogInImg.click();
        logoutButton.click();
    }

    public void trySearch(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.ENTER);
    }

    private List<WebElement> getChildren(WebElement element) {
        return element.findElements(
                new By.ByXPath("*")
        );
    }

    public List<WebElement> getCategoriesLinks() {
        return getChildren(categories);
    }

    public List<WebElement> getSubCategoriesLinks() {
        return getChildren(subCategories);
    }

    public List<WebElement> getQuestionsLinks() {
        return getChildren(questions);
    }

    public List<WebElement> getComments() {
        return getChildren(comments);
    }

    public void goToProfile() {
        afterLogInImg.click();
        profile.click();
    }

    public void createComment(String msg) {
        commentInput.clear();
        commentInput.sendKeys(msg);
        commentInput.sendKeys(Keys.ENTER);
    }

    public void extendComments() {
        moreComments.click();
    }

    public String getCommentText(WebElement comment) {
        return comment.findElement(new By.ByCssSelector("#secondaryColor")).getText();
    }

    public MainPage(WebDriver driver) {super(driver);}
}
