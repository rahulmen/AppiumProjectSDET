package com.learning.appium.Locator;

import com.learning.appium.Tests.AirtelXtreamTest;
import com.learning.appium.baseClasses.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.logging.Logger;

public class UserLoginInfoPage extends BaseClass{

    public static Logger logger = Logger.getLogger(UserLoginInfoPage.class.getName());

    private AppiumDriver<MobileElement> driver;

    /*
     * Constuctor to initialise the parent and local appium driver
     */
    public UserLoginInfoPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
        this.driver = driver;
    }

    //public UserLoginInfoPage(){super();}


    /*
     *   Page Object of UserLogin Page
     */
    private By header_note = MobileBy.xpath("//android.widget.TextView[@text='This section is all about you']");
    private By user_name = MobileBy.id("tv.accedo.airtel.wynk:id/welcomeHeader");

    public MobileElement headerNote(){
        return driver.findElement(header_note);
    }

    public MobileElement userName(){
        return driver.findElement(user_name);
    }

    /*
     * public method to return the current Object
     */
    public UserLoginInfoPage getUserLoginInfoPage(){
        if(getActionBarList()==null){
            setActionBarList(action_bars());
        }
        explicitWait().until(ExpectedConditions.elementToBeClickable(getActionBarList().get(6)));
        getActionBarList().get(6).click();
        if(!headerNote().isDisplayed()){
            getActionBarList().get(6).click();
        }
        explicitWait().until(ExpectedConditions.visibilityOf(headerNote()));
        return this;
    }



    public HomePage navigateToHomePage(){
        explicitWait().until(ExpectedConditions.elementToBeClickable(getActionBarList().get(5)));
        getActionBarList().get(5).click();
        explicitWait().until(ExpectedConditions.invisibilityOf(headerNote()));
        return new HomePage(driver);
    }


    public String validateUserInfo(){
        String actualUserName = null;
        if(headerNote().isDisplayed()){
            actualUserName = userName().getText();
        }
        return actualUserName;
    }


    public void navigateBack(){
        driver.navigate().back();
    }




}
