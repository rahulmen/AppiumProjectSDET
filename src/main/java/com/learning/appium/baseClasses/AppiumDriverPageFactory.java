package com.learning.appium.baseClasses;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class AppiumDriverPageFactory {



    public static AppiumDriver<MobileElement> getAppiumDriver(String driver, DesiredCapabilities desiredCapability) throws Exception{

        switch(driver){

            case "android":{
                 return new AndroidDriver<MobileElement>(desiredCapability);
            }
           case "ios":{
                return new IOSDriver<MobileElement>(desiredCapability);
            }
            default:{
                System.err.print("Incorrect Driver Information");
            }
        }
        return null;
    }

}
