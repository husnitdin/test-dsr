package com.example.dsrtest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

  @FindBy(name = "FirstName")
  public WebElement firstName;

  @FindBy(name = "LastName")
  public WebElement lastName;

  @FindBy(name = "Email")
  public WebElement email;

  @FindBy(name = "PhoneNumber")
  public WebElement phoneNumber;

  @FindBy(xpath = ("//input[@value='Male']"))
  public WebElement checkMale;

  @FindBy(xpath = ("//input[@value='Female']"))
  public WebElement checkFemale;

  @FindBy(name = "Agreement")
  public WebElement agreement;

  @FindBy(id = "99")
  public WebElement submit;

  public void LoginToDSR(
      String userFirstName,
      String userLastName,
      String userEmail,
      String userPhone,
      String gender) throws InterruptedException {
    firstName.sendKeys(userFirstName);
    Thread.sleep(1000);
    lastName.sendKeys(userLastName);
    Thread.sleep(1000);
    email.sendKeys(userEmail);
    Thread.sleep(1000);
    phoneNumber.sendKeys(userPhone);
    Thread.sleep(1000);
    if (gender.equalsIgnoreCase("male")) {
      checkMale.click();
    } else {
      checkFemale.click();
    }
    Thread.sleep(1000);
    agreement.click();
    Thread.sleep(1000);
    submit.sendKeys(Keys.ENTER);
  }
}
