package com.mmt.helpers;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesHelper {
    @NotNull
    public static DesiredCapabilities getDesiredCapabilities(String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        return capabilities;
    }
}
