package com.example.dsrtest.testcases;

import com.example.dsrtest.pages.BaseClass;
import com.example.dsrtest.pages.LoginPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Login extends BaseClass {

  @Test(priority = 1)
  public void LoginTo() throws InterruptedException {

    logger = reporter.createTest("Login to DSR");
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    logger.info("Starting the application");

    loginPage.LoginToDSR(
        excel.getStringData("Login", 0, 1),
        excel.getStringData("Login", 1, 1),
        excel.getStringData("Login", 2, 1),
        excel.getStringData("Login", 3, 1),
        excel.getStringData("Login", 4, 1));

    Alert alert = driver.switchTo().alert();

    String alertText = alert.getText();

    alert.accept();
    System.out.println(alertText);
    logger.pass("Successfully logged in");
  }
}
