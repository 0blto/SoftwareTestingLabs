package com.drainshawty.lab3.answers;

import com.drainshawty.lab3.Page;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfilePage extends Page {

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[3]/button[2]")
    WebElement editButton;

    @FindBy(xpath = "//*[@id=\"simple-upload\"]")
    WebElement imgInput;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[2]/div[2]/textarea")
    WebElement usernameInput;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div/div[2]/div/div[1]/button[2]")
    WebElement submit;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[1]/span/img")
    @Getter
    WebElement imgElement;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[1]/div[2]/h1")
    @Getter
    WebElement usernameHeader;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[4]/div")
    @Getter
    WebElement info;

    void uploadPhoto(String filePath) {
        editButton.click();
        imgInput.sendKeys(filePath);
        submit.click();
    }

    void changeUsername(String username) {
        editButton.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);
        submit.click();
    }
    public ProfilePage(WebDriver driver) {super(driver);}
}
