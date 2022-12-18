package com.simbirsoft.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.simbirsoft.config.CredentialConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

public class SelenoidMobileDriver implements WebDriverProvider {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);
    public static URL getAppiumServerUrl() {
        try {
            return new URL(format("https://%s:%s@%s/wd/hub", credentials.selenoid_user(), credentials.selenoid_password(), credentials.selenoid_url()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "android");
        desiredCapabilities.setCapability("version", "8.1");
        desiredCapabilities.setCapability("locale", "en");
        desiredCapabilities.setCapability("language", "en");
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", true);
        desiredCapabilities.setCapability("appPackage", "de.danoeh.antennapod");
        desiredCapabilities.setCapability("appActivity", "de.danoeh.antennapod.activity.MainActivity");
        desiredCapabilities.setCapability("app", apkUrl());

        return new AndroidDriver(getAppiumServerUrl(), desiredCapabilities);
    }

    private URL apkUrl() {
        try {
            return new URL("https://f-droid.org/repo/de.danoeh.antennapod_2070195.apk");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
