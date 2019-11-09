package com.learning.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.learning.appium.BaseClass;

import java.net.URL;
public class AppiumDriverPageFactory {

    private static BaseClass baseClass = BaseClass.getInstance();


    public static AndroidDriver<MobileElement> getAppiumDriver(String driver, DesiredCapabilities desiredCapability) throws Exception{

        switch(driver){

            case "android":{
                 return new AndroidDriver<MobileElement>(baseClass.getURL(),desiredCapability);
            }
           /* case "ios":{
                return new IOSDriver<MobileElement>(new URL(Constrants.appiumDefailtAddress),desiredCapability);
            }*/
            default:{
                System.err.print("Incorrect Driver Information");
            }
        }
        return null;
    }

}
