package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class QueuePage {
    private final SelenideElement
            menuButton = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Open menu\"]")),
            openQueuePageButton = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle")).findBy(Condition.text("Queue")),
            optionsButton = $(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")),
            emptyPageTitle = $(MobileBy.id("de.danoeh.antennapod:id/emptyViewTitle")),
            emptyPageMessage =  $(MobileBy.id("de.danoeh.antennapod:id/emptyViewMessage"));

    private final ElementsCollection
            queueItems = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle")),
            options = $$(MobileBy.id("de.danoeh.antennapod:id/title"));

    @Step("Открытие страницы очереди")
    public QueuePage openPage() {
        menuButton.click();
        openQueuePageButton.click();

        return this;
    }

    @Step("Проверка эпизода в очереди")
    public QueuePage checkEpisodeInQueue(String episode) {
        queueItems.shouldHave(CollectionCondition.itemWithText(episode));

        return this;
    }

    @Step("Очистка очереди")
    public QueuePage clearQueue() {
        optionsButton.click();
        options.findBy(Condition.text("Clear Queue")).click();
        Selenide.confirm();

        return this;
    }

    @Step("Проверка пустой очереди")
    public QueuePage checkEmptyQueue() {
        emptyPageTitle.shouldBe(Condition.visible);
        emptyPageTitle.shouldHave(Condition.text("No queued episodes"));
        emptyPageMessage.shouldBe(Condition.visible);
        emptyPageMessage.shouldHave(Condition.text("Add an episode by downloading it, or long press an episode and select \"Add to queue\"."));

        return this;
    }
}
