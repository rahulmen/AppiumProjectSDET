package com.learning.appium;

import com.sun.istack.internal.logging.Logger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Program to Open Calculator on reak device using Apium
 *
 */
public class OpenChrome extends BaseClass{


    public static com.sun.istack.internal.logging.Logger logger = Logger.getLogger(AppiumAPiDemos.class);

    private BaseClass baseClass = BaseClass.getInstance();
    private static AppiumDriver<MobileElement> appiumDriver;
    InetAddress inetAddress;


    public void openCalculator() throws Exception{


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        inetAddress = InetAddress.getLocalHost();
        /*
        Set Device related capability
         */
        desiredCapabilities.setCapability("automationName",AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability("deviceName","OnePlus 7");
        desiredCapabilities.setCapability("udid","db74194f");
        //desiredCapabilities.setCapability("udid",inetAddress.getHostAddress()+":5555"");
        desiredCapabilities.setCapability("platformName", Platform.ANDROID);
        desiredCapabilities.setCapability("platformVersion","9");
        desiredCapabilities.setCapability("unlockType","pin");
        desiredCapabilities.setCapability("unlockKey","411028");
        desiredCapabilities.setCapability("autoWebview","false");
        desiredCapabilities.setCapability("noReset","true");
        /*
         * Set App Related Capability
         */

        desiredCapabilities.setCapability("appPackage","com.android.chrome");
        desiredCapabilities.setCapability("appActivity","org.chromium.chrome.browser.document.ChromeLauncherActivity");

        /*
         * Not mandatory Capability
         */

        //desiredCapabilities.setCapability("app","path of apk");
        // this Capability is not mandatory if we have defined appPackage and appActivity

        //desiredCapabilities.setCapability("browserName", BrowserType.FIREFOX);
        // Server will create a session if we dont provide appPackage and appActivity but provide BrowserType and need to be empty if automating Native or Hybrid App

        //desiredCapabilities.setCapability("otherApps","[]");
        //If we want other Apps to be installed prior to test Execution.

        //desiredCapabilities.setCapability("orientation", ScreenOrientation.LANDSCAPE);
        //to set orientation of the device while creating a session.

        //desiredCapability.setCapability("fullReset","false");
        //Perform reset if Capability is set to true.



        //URL where appium is running (Appium Server EndPoint)
        //URL url = new URL(" http://127.0.0.1:4723/wd/hub");

        //Initiate Appium Driver (Intitaite Post REQUEST to create a APPIUM Session id)
        appiumDriver = new AndroidDriver<MobileElement>(baseClass.getURL(),desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


    }

    @Test
    public static void main() {

        OpenChrome openChrome = new OpenChrome();
        try {
            openChrome.openCalculator();
            System.out.println("Application Started");
            appiumDriver.get("https://www.google.com");
            appiumDriver.navigate().to("https://www.facebook.com");
            logger.info(appiumDriver.getCurrentUrl()+"");
        }
        catch(Exception nsee){
            System.out.println(nsee.getCause());
            System.out.println(nsee.getMessage());
            nsee.printStackTrace();
        }
    }

}
