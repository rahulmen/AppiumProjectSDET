package com.learning.appium.Locator;

import com.learning.appium.Tests.AirtelXtreamTest;
import com.learning.appium.baseClasses.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class LiveTVPage extends BaseClass{

    public static Logger logger = Logger.getLogger(LiveTVPage.class.getName());

    private AppiumDriver<MobileElement> driver;

    public LiveTVPage(AppiumDriver<MobileElement> driver)
    {
        super(driver);
        this.driver = driver;
    }

    List<MobileElement> liveChannels = null;

    public List<MobileElement> getLiveChannels() {
        return liveChannels;
    }

    public void setLiveChannels(List<MobileElement> liveChannels) {
        this.liveChannels = liveChannels;
    }

    private By voice_search = MobileBy.id("tv.accedo.airtel.wynk:id/voice_search");
    private By liveChannel = MobileBy.xpath("//*[@class='android.view.ViewGroup']");
    private By playerContext = MobileBy.xpath("//*[@resource-id='tv.accedo.airtel.wynk:id/playerViewConBorder']");
    private By playerPlay = MobileBy.xpath("//*[@resource-id='tv.accedo.airtel.wynk:id/player_control_play1']");
    private By playerForward = MobileBy.xpath("//*[@resource-id='tv.accedo.airtel.wynk:id/ic_forward_middle']");

    public MobileElement voiceSearch(){
        return driver.findElement(voice_search);
    }

    public MobileElement liveChannel(){
        return driver.findElement(liveChannel);
    }

    public List<MobileElement> liveChannelList(){
        liveChannels=driver.findElements(liveChannel);
        return liveChannels;
    }

    public MobileElement playerContext(){
        return driver.findElement(playerContext);
    }

    public MobileElement playerPlay(){
        return driver.findElement(playerPlay);
    }

    public MobileElement playerForward(){
        return driver.findElement(playerForward);
    }

    //public LiveTVPage(){super();}

    public LiveTVPage getLivePage(){
        if(getActionBarList()==null){
            setActionBarList(action_bars());
        }
        explicitWait().until(ExpectedConditions.elementToBeClickable(getActionBarList().get(1)));
        getActionBarList().get(1).click();
        if(!voiceSearch().isDisplayed()){
            getActionBarList().get(1).click();
        }
        return this;
    }

    public void checkLivePage(){
        checkElementVisibility(voiceSearch());
        checkElementVisibility(liveChannel());
    }

    public void playRandomChannel(){
        sleep(2000);
        explicitWait().until(ExpectedConditions.visibilityOf(liveChannel()));
        if(liveChannels==null){
            setLiveChannels(liveChannelList());
        }
        Random rand = new Random();
        MobileElement randomElement = liveChannels.get(rand.nextInt(liveChannels.size()));
        explicitWait().until(ExpectedConditions.elementToBeClickable(randomElement));
        randomElement.click();
        sleep(8000);
    }

    public void pausePlayCurrentChannel(){
        explicitWait().until(ExpectedConditions.elementToBeClickable(playerContext()));
        playerContext().click();
        explicitWait().until(ExpectedConditions.elementToBeClickable(playerPlay()));
        playerPlay().click();
        sleep(5000);
    }

    public void forwardCurrentChannel(){
        explicitWait().until(ExpectedConditions.elementToBeClickable(playerContext()));
        playerContext().click();
        explicitWait().until(ExpectedConditions.elementToBeClickable(playerForward()));
        playerForward().click();
        sleep(2000);
    }

    public void navigateBack(){
        driver.navigate().back();
    }



}
