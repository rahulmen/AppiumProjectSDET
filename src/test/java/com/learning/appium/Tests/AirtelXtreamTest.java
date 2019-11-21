/*
* Basic DesiredCapability
platformName: the name of the platform to automate
platformVersion: the version of the platform to automate
deviceName: the kind of device to automate
app: the path to the app you want to automate (but use the browserName capability instead in the case of automating a web browser)
automationName: the name of the driver you wish to use
 */

package com.learning.appium.Tests;

import com.learning.appium.Locator.LiveTVPage;
import com.learning.appium.baseClasses.AppiumDriverPageFactory;
import com.learning.appium.baseClasses.DesiredCapability;
import com.learning.appium.Locator.HomePage;
import com.learning.appium.Locator.UserLoginInfoPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AirtelXtreamTest extends HomePage {

    public static Logger logger = Logger.getLogger(AirtelXtreamTest.class.getName());

     DesiredCapabilities desiredCapabilities;
     AppiumDriver<MobileElement> appiumDriver;

    public AirtelXtreamTest(){
        super();
    };


    public AirtelXtreamTest(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    String expectedUserName = "Rahul";

    @BeforeTest
    public void setCapability() throws Exception {

        desiredCapabilities = DesiredCapability.getInstance();
        desiredCapabilities.setCapability("deviceName","OnePlus 7");
        desiredCapabilities.setCapability("udid","db74194f");
        //desiredCapabilities.setCapability("udid","10.17.14.187:5555");
        desiredCapabilities.setCapability("platformVersion","9");
        desiredCapabilities.setCapability("automationName", AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability("platformName", Platform.ANDROID);
        desiredCapabilities.setCapability("autoAcceptAlerts", true);
        desiredCapabilities.setCapability("appPackage", "tv.accedo.airtel.wynk");
        desiredCapabilities.setCapability("appActivity", "tv.accedo.airtel.wynk.presentation.view.activity.SplashActivity");
        desiredCapabilities.setCapability("unlockType", "pin");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("unlockKey", "411028");
        desiredCapabilities.setCapability("autoGrantPermissions", "true"); //To provide permission to app
        appiumDriver = AppiumDriverPageFactory.getAppiumDriver("android", desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Set Implicit Wait
    }


    @Test
    public void LiveTVAirtelXtreamTests(){
        logger.info("Application is Launched");
        AirtelXtreamTest airtelXtreamTest = new AirtelXtreamTest(appiumDriver);
        HomePage homePage = airtelXtreamTest.getHomePage();
        homePage.checkIsHomePage();

        logger.info("Navigating to User Profile Page");
        UserLoginInfoPage userLoginInfoPage = new UserLoginInfoPage(appiumDriver);
        userLoginInfoPage.getUserLoginInfoPage();
        String actualName = userLoginInfoPage.validateUserInfo();
        logger.info("Navigated to User Profile Page");

        logger.info("Validating User Login Information");
        Assert.assertEquals(actualName,expectedUserName);
        logger.info("User Login Information Validated");

        logger.info("Navigating Back to Home Page");
        homePage.getHomePage();
        homePage.checkIsHomePage();
        logger.info("Navigated Back to Home Page");

        logger.info("Navigating to LiveTV Page");
        LiveTVPage liveTVPage = new LiveTVPage(appiumDriver);
        liveTVPage.getLivePage();
        liveTVPage.checkLivePage();
        logger.info("Navigated to LiveTV Page");


        logger.info("Playing random LiveTV Channel");
        liveTVPage.playRandomChannel();
        logger.info("Random LiveTV Channel played");

        logger.info("Performing Pause on LiveTV Channel");
        liveTVPage.pausePlayCurrentChannel();
        logger.info("LiveTV Channel Paused");

        logger.info("Forwarding Pause LiveTV Channel");
        liveTVPage.forwardCurrentChannel();
        logger.info( "LiveTV Channel forwarded");

        logger.info("Playing back LiveTV Channel");
        liveTVPage.pausePlayCurrentChannel();
        logger.info("LiveTV Channel channel resumed");

        logger.info("Minimise LiveTV Channel");
        liveTVPage.navigateBack();
        logger.info("LiveTV Channel Minimised");

        logger.info("Navigating Back to Home Page");
        homePage.getHomePage();
        homePage.checkIsHomePage();
        logger.info("Navigated back to Home Page");

        logger.info("Closing app");
        appiumDriver.closeApp();

    }

}
