package com.example.dsrtest.pages;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.example.dsrtest.utilities.BrowserFactory;
import com.example.dsrtest.utilities.ConfigDataProvider;
import com.example.dsrtest.utilities.ExcelDataProvider;
import com.example.dsrtest.utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;

import static com.example.dsrtest.utilities.Helper.getCurrentDateTime;

public class BaseClass {

    public WebDriver driver;
    public ExcelDataProvider excel;
    public ConfigDataProvider config;
    public ExtentReports reporter;
    public ExtentTest logger;

    @BeforeSuite
    public void setUpBeforeSuite() {
        excel = new ExcelDataProvider();
        config = new ConfigDataProvider();

        ExtentHtmlReporter extent = new ExtentHtmlReporter(
                new File("./Reports/"
                        + getCurrentDateTime()
                        + ".html"));
        reporter = new ExtentReports();
        reporter.attachReporter(extent);
    }

    @AfterMethod
    public void screenShotBeforeTearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                logger.fail("Test failed",
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                Helper.captureScreenshots(driver)).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(result.getStatus() == ITestResult.SUCCESS){
            try {
                logger.pass("Test passed",
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                Helper.captureScreenshots(driver)).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(result.getStatus() == ITestResult.SKIP){
            try {
                logger.skip("Test skipped",
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                Helper.captureScreenshots(driver)).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reporter.flush();
    }

    @BeforeClass ( )
    public void setup() {
        driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getURL());
    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.quitBrowser(driver);
    }

}
