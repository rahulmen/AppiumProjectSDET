package com.learning.appium.baseClasses;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;
import java.util.List;

public class BaseClass {

    private AppiumDriver<MobileElement> driver;
    public AppiumDriverLocalService appiumDriverLocalService;
    List<MobileElement> actionBarList;

    public BaseClass(AppiumDriver<MobileElement> driver){
        this.driver=driver;
    }

    public List<MobileElement> getActionBarList() {
        return actionBarList;
    }

    public void setActionBarList(List<MobileElement> actionBarList) {
        this.actionBarList = actionBarList;
    }

    private By action_bar = MobileBy.xpath("//*[@class='android.support.v7.app.ActionBar$Tab']");

    public List<MobileElement> action_bars(){
        actionBarList =  driver.findElements(action_bar);
        return actionBarList;
    }

    public BaseClass(){super();}

    @BeforeSuite
    public void setup(){
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.usingAnyFreePort();
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception{
        appiumDriverLocalService.stop();
        Runtime.getRuntime().exec("killall -KILL node");
    }

    public URL getURL(){
        return appiumDriverLocalService.getUrl();
    }


    protected WebElement scrollUntilFound(String text)
    {
        WebElement element = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
        return element;
    }


    public WebDriverWait explicitWait(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        return wait;
    }

    public boolean checkElementVisibility(MobileElement element){
        explicitWait().until(ExpectedConditions.visibilityOf(element));
        if(element.isDisplayed()){
            return true;
        }else{
            return false;
        }
    }


    public void sleep(long seconds){
        try {
            Thread.sleep(seconds);
        }catch(InterruptedException nsee){
            nsee.printStackTrace();
        }
    }

    public void naviagateBack() {
        driver.navigate().back();
    }

    /*public void clickLoopParticularElement(MobileElement mobileElement){
        WebDriverWait wait = new WebDriverWait(androidDriver,30);

        boolean result = wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply( WebDriver webDriver) {
                if(mobileElement.isDisplayed()){
                    mobileElement.click();
                }
                if(mobileElement.isSelected()==true){
                    return true;
                }else{
                    return false;
                }

            }
        });*/
    }

