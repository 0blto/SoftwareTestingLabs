package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.Page;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainPage extends Page {

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

    public void logout() {
        afterLogInImg.click();
        logoutButton.click();
    }

    public MainPage(WebDriver driver) {super(driver);}
}
