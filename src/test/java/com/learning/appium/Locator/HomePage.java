package com.learning.appium.Locator;

import com.learning.appium.Tests.AirtelXtreamTest;
import com.learning.appium.baseClasses.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.logging.Logger;

public class HomePage extends BaseClass {

    public static Logger logger = Logger.getLogger(HomePage.class.getName());

    AppiumDriver<MobileElement> driver;

    public HomePage(){super();}

    public HomePage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By voice_search = MobileBy.id("tv.accedo.airtel.wynk:id/voice_search");

    public MobileElement voiceSearch(){
        return driver.findElement(voice_search);
    }

    public HomePage getHomePage(){
        if(getActionBarList()==null){
            setActionBarList(action_bars());
        }
        explicitWait().until(ExpectedConditions.elementToBeClickable(getActionBarList().get(5)));
        getActionBarList().get(5).click();
        if(voiceSearch().isDisplayed()){
            getActionBarList().get(5).click();
        }
        return this;
    }

    public void checkIsHomePage() {
        checkElementVisibility(voiceSearch());
    }

    public UserLoginInfoPage navigateToUserProfile() {

        if(getActionBarList()==null){
          setActionBarList(action_bars());
        }
        explicitWait().until(ExpectedConditions.elementToBeClickable(getActionBarList().get(6)));
        getActionBarList().get(6).click();
        return new UserLoginInfoPage(driver);
    }

    public void navigateBack(){
        driver.navigate().back();
    }





}
