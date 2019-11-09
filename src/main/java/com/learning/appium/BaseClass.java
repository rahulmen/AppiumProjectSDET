package com.learning.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public class BaseClass {


    private BaseClass baseClass;

    public static BaseClass getInstance(){
        return new BaseClass();
    }

    private static AppiumDriverLocalService appiumDriverLocalService;


    @BeforeSuite
    public void setup(){
        appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
        appiumDriverLocalService.start();
    }

     /*@BeforeSuite(alwaysRun = true)
    public void startAppium(){
        try{
            appiumDriverLocalService.isRunning();
            }
        catch(NullPointerException nsee){
            desiredCapabilities=DesiredCapability.getInstance();
            AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
            appiumServiceBuilder.withCapabilities(desiredCapabilities);
            appiumServiceBuilder.withCapabilities(desiredCapabilities);
            appiumServiceBuilder.usingAnyFreePort();
            appiumServiceBuilder.withIPAddress(Constrants.localhost);
            appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
            appiumDriverLocalService = appiumServiceBuilder.build();
            appiumDriverLocalService.start();
        }
        }*/


    @AfterSuite
    public void tearDown(){
        appiumDriverLocalService.stop();
    }

    public URL getURL(){
        return appiumDriverLocalService.getUrl();
    }

}
