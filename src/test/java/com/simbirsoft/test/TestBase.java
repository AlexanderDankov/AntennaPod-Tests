package com.simbirsoft.test;

import com.codeborne.selenide.Configuration;
import com.simbirsoft.drivers.EmulatorMobileDriver;
import com.simbirsoft.pages.SearchPage;
import com.simbirsoft.pages.SubscriptionsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    SearchPage searchPage = new SearchPage();
    SubscriptionsPage subscriptionsPage = new SubscriptionsPage();

    @BeforeAll
    public static void setup() {
        Configuration.browser = EmulatorMobileDriver.class.getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
