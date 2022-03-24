package com.example.dsrtest.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {

        if(driver==null){
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();

            } else if (browserName.equalsIgnoreCase("opera")) {
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
            } else {
                System.err.println("Given driver not exist");
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(appURL);

        return driver;
    }

    public static void quitBrowser(WebDriver driver) {
        System.out.println("Quitting the browser");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void closeDriver(WebDriver driver) {
        System.out.println("Closing the browser");
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
