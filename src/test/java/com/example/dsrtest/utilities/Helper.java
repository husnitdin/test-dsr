package com.example.dsrtest.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {

    public static String captureScreenshots(WebDriver driver) {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "./Screenshots/" + getCurrentDateTime() + ".png";
        try {
            FileUtils.copyFile(screenshotAs, new File(screenshotPath));
            System.out.println("Screenshot captured successfully");
        } catch (IOException e) {
            System.out.println("Unable to capture " + e.getMessage());
        }
        return screenshotPath;
    }

    public static String getCurrentDateTime() {
        DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        return customFormat.format(new Date());
    }

    public static List<String> getEachElementText(List<WebElement> list) {

        List<String> webElementsAsString = new ArrayList<>();
        for (WebElement each : list) {
            webElementsAsString.add(each.getText());
        }
        return webElementsAsString;
    }

    public static void implWait(WebDriver driver, int time) {
        // waits up to 10 seconds until it finds the element
        // checks in every 500 ms / Polling frequency is 500 ms
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    public static void explWait(WebDriver driver, int time, ExpectedCondition<WebElement> webElementExpectedCondition) {
        // waits until certain condition is met
        // checks in every 500 ms / Polling frequency is 500 ms
        WebDriverWait wait = new WebDriverWait(driver, time);

        try {

//            WebElement until = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Never Stop Learning ...")));
            WebElement until = wait.until(webElementExpectedCondition);
            until.click();
            Thread.sleep(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fluWait(WebDriver driver, ExpectedCondition<WebElement> webElementExpectedCondition) {

        // same as explicit wait
        // difference is you can customize polling frequency
        try {

            FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);

            fluentWait.until(webElementExpectedCondition).click();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void verifyLinks(String urlLink) {

        try {
            URL link = new URL(urlLink);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(2000);
            httpConn.connect();
            if (httpConn.getResponseCode() == 200) {
                System.out.println(urlLink + " - " + httpConn.getResponseMessage());
            }
            if (httpConn.getResponseCode() == 404) {
                System.out.println(urlLink + " - " + httpConn.getResponseMessage());
            }
        } catch (Exception e) {
            System.out.println("Exception thrown " + e.getMessage());
        }
    }

}