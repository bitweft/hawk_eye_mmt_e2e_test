package com.mmt.helpers;

import com.mmt.enums.Browser;
import com.mmt.enums.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.mmt.helpers.CapabilitiesHelper.getDesiredCapabilities;
import static com.mmt.helpers.HubUrlHelper.getUrl;

public class DriverHelper {
    private static ThreadLocal<WebDriver> webDriverThreadLocal;

    public static void createDriver(String run, String browserName, String hubUrl) {
        WebDriver driver;
        if (run.equals(Environment.REMOTE.name().toLowerCase())) {
            driver = getRemoteDriver(browserName, hubUrl);
        } else {
            driver = getLocalDriver(browserName);
        }
        setDriver(driver);
    }

    public static WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    private static void setDriver(WebDriver driver) {
        if (webDriverThreadLocal == null) {
            webDriverThreadLocal = ThreadLocal.withInitial(() -> driver);
            webDriverThreadLocal.set(driver);
        }
        webDriverThreadLocal.set(driver);
    }

    @NotNull
    private static WebDriver getRemoteDriver(String browserName, String hubUrl) {
        DesiredCapabilities capabilities = getDesiredCapabilities(browserName);
        return new RemoteWebDriver(getUrl(hubUrl), capabilities);
    }

    private static WebDriver getLocalDriver(String browserName) {
        if (browserName.equals(Browser.CHROME.name().toLowerCase())) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }
}
