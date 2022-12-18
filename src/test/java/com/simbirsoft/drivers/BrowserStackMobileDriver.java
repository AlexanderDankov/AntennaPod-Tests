package com.simbirsoft.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.simbirsoft.config.CredentialConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

public class BrowserStackMobileDriver implements WebDriverProvider {

    public static CredentialConfig credentials = ConfigFactory.create(CredentialConfig.class);

    public static URL getBrowserStackUrl() {
        try {
            return new URL(format("http://%s/wd/hub", credentials.browserstack_url()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability("browserstack.user", credentials.browserstack_user());
        desiredCapabilities.setCapability("browserstack.key", credentials.browserstack_key());
        desiredCapabilities.setCapability("app", "bs://c7bf294a3184b01db958fa64df7bb63d7e41c249");
        desiredCapabilities.setCapability("device", "Samsung Galaxy S22");
        desiredCapabilities.setCapability("os_version", "12.0");
        desiredCapabilities.setCapability("locale", "en");
        desiredCapabilities.setCapability("language", "en");
        desiredCapabilities.setCapability("project", "AntennaPod tests Project");
        desiredCapabilities.setCapability("build", "Java Android");
        desiredCapabilities.setCapability("name", "AntennaPod Test");

        return new AndroidDriver(getBrowserStackUrl(), desiredCapabilities);
    }
}
