/*
* Basic DesiredCapability
platformName: the name of the platform to automate
platformVersion: the version of the platform to automate
deviceName: the kind of device to automate
app: the path to the app you want to automate (but use the browserName capability instead in the case of automating a web browser)
automationName: the name of the driver you wish to use
 */

package com.learning.appium;

import com.google.common.base.Function;
import com.sun.istack.internal.logging.Logger;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumAPiDemos extends BaseClass{

    public static Logger logger = Logger.getLogger(AppiumAPiDemos.class);

    private static DesiredCapabilities desiredCapabilities;
    private static AndroidDriver<MobileElement> appiumDriver;

    @BeforeTest
    public void setCapability() throws Exception{
        desiredCapabilities=DesiredCapability.getInstance();

        desiredCapabilities.setCapability("platformName", Platform.ANDROID);
        //desiredCapabilities.setCapability("udid","db74194f");
        desiredCapabilities.setCapability("udid","10.17.3.46:5555");
        desiredCapabilities.setCapability("platformVersion","9");
        desiredCapabilities.setCapability("deviceName","OnePlus 7");
        desiredCapabilities.setCapability("automationName", AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability("appPackage","io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity","io.appium.android.apis.ApiDemos");
        desiredCapabilities.setCapability("unlockType", "pin");
        desiredCapabilities.setCapability("unlockKey","411028");
        desiredCapabilities.setCapability("noReset","true");
        desiredCapabilities.setCapability("autoGrantPermissions","true"); //To provide permission to app

        //desiredCapabilities.setCapability("","");
        appiumDriver = AppiumDriverPageFactory.getAppiumDriver("android",desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //Set Implicit Wait
    }


    @Test
    public void addWifi() throws Exception{
        logger.info("Application Launched");
        //Xpath for Preference is //tagName[@attribute=value]
        MobileElement webElement=null;

        webElement = appiumDriver.findElementByXPath("//android.widget.TextView[@text='Preference']");
        Assert.assertTrue(webElement.isDisplayed());
        webElement.click();

        logger.info("Clicked on element");
        webElement = appiumDriver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']");
        Assert.assertTrue(webElement.isDisplayed());
        webElement.click();


        webElement = appiumDriver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']");
        if(!webElement.isEnabled()) {
            webElement = appiumDriver.findElementByClassName("android.widget.CheckBox");
            Assert.assertTrue(webElement.isDisplayed());
            if(!webElement.isSelected()){
                webElement.click();
            }
        }

        webElement = appiumDriver.findElementByXPath("( //android.widget.RelativeLayout)[2]");
        Assert.assertTrue(webElement.isEnabled() && webElement.isDisplayed());
        webElement.click();

        webElement= appiumDriver.findElementById("android:id/edit");
        Assert.assertTrue(webElement.isEnabled() && webElement.isDisplayed());
        webElement.sendKeys("Demo Wifi");

        webElement = appiumDriver.findElementByXPath("//android.widget.Button[@text='OK']");
        Assert.assertTrue(webElement.isDisplayed());
        webElement.click();

        webElement = appiumDriver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']");
        Assert.assertTrue(webElement.isEnabled() && webElement.isDisplayed());
        webElement.click();

        webElement= appiumDriver.findElementById("android:id/edit");
        Assert.assertNotNull(webElement.getText());

        webElement = appiumDriver.findElementByXPath("//android.widget.Button[@text='OK']");
        Assert.assertTrue(webElement.isDisplayed());
        webElement.click();

    }


    @Test
    public void tabAction(){

        TouchAction touchAction = new TouchAction(appiumDriver);
        MobileElement webElement = appiumDriver.findElementByXPath("//android.widget.TextView[@text='Preference']");
        //Perform Tab Operation.
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(webElement))).perform();

    }


    @Test
    public void checkIsClickable(){

        MobileElement element = null;
        element = appiumDriver.findElementByAndroidUIAutomator("text(\"Preference\")");
        element.click();

        List<MobileElement> result = appiumDriver.findElementsByAndroidUIAutomator("new UiSelector().clickable(false)");


        for(int i=0;i<result.size();i++){
            if(result.get(i).isDisplayed()){
                result.get(i).click();
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException nsee){
                    nsee.printStackTrace();
                }
                appiumDriver.navigate().back();

                }
        }

    }

    @Test
    public void UIAutomatorTest(){
        MobileElement element = appiumDriver.findElementByAndroidUIAutomator("text(\"Views\")");
        if(element.isDisplayed() && element.isEnabled())
        element.click();
        List<MobileElement> elements = appiumDriver.findElementsByXPath("//android.widget.TextView[@resource-id='android:id/text1']");
        for(int i=0;i<elements.size();i++){
            System.out.print(elements.get(i).getId());
        }
    }


    @Test
    public void uiAutomator(){
        List<MobileElement> elements = appiumDriver.findElementsByAndroidUIAutomator("new UiSelector().enabled(true)");

        System.out.print(elements.size());
        for(int i=0;i<elements.size();i++){
           logger.info(elements.get(i).isDisplayed()+" ");
        }


    }            
}
