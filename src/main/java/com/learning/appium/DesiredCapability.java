package com.learning.appium;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapability {

    private DesiredCapability(){}

    private static DesiredCapabilities desiredCapability ;


    public static DesiredCapabilities getInstance() {

        if (desiredCapability == null) {
            synchronized (DesiredCapability.class) {
                if (desiredCapability == null) {
                    desiredCapability = new DesiredCapabilities();
                }
            }
        }
        return desiredCapability;
    }


}
