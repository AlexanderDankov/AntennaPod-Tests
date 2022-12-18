package com.simbirsoft.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SettingsPage {

    private final SelenideElement
            settingsButton = $(MobileBy.id("de.danoeh.antennapod:id/nav_settings")),
            notificationsSettings = $$(MobileBy.id("android:id/title")).findBy(Condition.text("Notifications")),
            allNotificationsSwitch = $$(MobileBy.id("com.android.settings:id/switch_text")).findBy(Condition.text("All AntennaPod notifications")),
            mainCheckBox = $(MobileBy.id("com.android.settings:id/switch_widget")),
            contentMessage = $(MobileBy.id("android:id/title"));

    @Step("Открытие страницы с настройками")
    public SettingsPage openPage() {
        settingsButton.click();

        return this;
    }

    @Step("Открытие настроек уведомлений")
    public SettingsPage openNotificationsSettings() {
        notificationsSettings.click();

        return this;
    }

    @Step("Отключение всех уведомлений")
    public SettingsPage disableAllNotifications() {
        allNotificationsSwitch.click();
        mainCheckBox
                .shouldHave(Condition.attribute("checked", "false"));

        return this;
    }

    @Step("Проверка отключенных уведомлений")
    public SettingsPage checkDisableNotificationsMessage() {
        contentMessage
                .shouldHave(Condition.text("At your request, Android is blocking this app's notifications from appearing on this device"));

        return this;
    }
}
