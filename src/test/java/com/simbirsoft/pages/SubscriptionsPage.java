package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class SubscriptionsPage {

    private final SelenideElement
            menuButton = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Open menu\"]")),
            openSubscriptionsPage = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle")).findBy(Condition.text("Subscriptions")),
            optionsButton = $(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")),
            removeButton = $$(MobileBy.id("de.danoeh.antennapod:id/title")).findBy(Condition.text("Remove podcast")),
            addToQueueButton = $$(MobileBy.id("de.danoeh.antennapod:id/title")).findBy(Condition.text("Add to Queue")),
            addToFavoritesButton = $$(MobileBy.id("de.danoeh.antennapod:id/title")).findBy(Condition.text("Add to Favorites")),
            emptyViewTitle = $(MobileBy.id("de.danoeh.antennapod:id/emptyViewTitle")),
            emptyViewMessage = $(MobileBy.id("de.danoeh.antennapod:id/emptyViewMessage")),
            renameButton = $$(MobileBy.id("de.danoeh.antennapod:id/title")).findBy(Condition.text("Rename podcast")),
            titleField = $(MobileBy.id("de.danoeh.antennapod:id/urlEditText")),
            confirmButton = $(MobileBy.id("android:id/button1")),
            podcastTitle = $(MobileBy.id("de.danoeh.antennapod:id/txtvTitle")),
            filterButton = $(MobileBy.id("de.danoeh.antennapod:id/butFilter")),
            favoritesFilter = $$(MobileBy.id("de.danoeh.antennapod:id/filter_dialog_radioButton1")).findBy(Condition.text("Is favorite"));
    private final ElementsCollection
            subscriptionsList = $$(MobileBy.id("de.danoeh.antennapod:id/imgvCover")),
            episodesList = $$(MobileBy.id("de.danoeh.antennapod:id/container")),
            episodesTitles = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle"));

    @Step("Открытие страницы подписок")
    public SubscriptionsPage openPage() {
        menuButton.click();
        openSubscriptionsPage.click();

        return this;
    }

    @Step("Проверка подкаста в подписках по названию")
    public SubscriptionsPage checkPodcastInSubscriptionsList(String title) {
        subscriptionsList.shouldHave(CollectionCondition
                .anyMatch("Check podcast in subscription list", c ->
                        c.getAttribute("content-desc").equals(title)));

        return this;
    }

    @Step("Открытие подкаста")
    public SubscriptionsPage openPodcast(String title) {
        subscriptionsList.findBy(Condition.attribute("content-desc", title)).click();

        return this;
    }

    @Step("Открытие настроек подкаста")
    public SubscriptionsPage openPodcastOptions() {
        optionsButton.click();

        return this;
    }

    @Step("Удаление подкаста из подписок")
    public SubscriptionsPage removePodcast() {
        removeButton.click();
        Selenide.confirm();

        return this;
    }

    @Step("Проверка пустого списка подписок")
    public SubscriptionsPage checkEmptySubscriptionsList() {
        emptyViewTitle.shouldHave(Condition.text("No subscriptions"));
        emptyViewMessage.shouldHave(Condition.text("To subscribe to a podcast, press the plus icon below."));

        return this;
    }

    @Step("Переименование подкаста")
    public SubscriptionsPage renamePodcast(String renameTitle) {
        renameButton.click();
        titleField.setValue(renameTitle);
        confirmButton.click();

        podcastTitle.shouldHave(Condition.text(renameTitle));

        return this;
    }

    @Step("Добавление первого эпизода подкаста в очередь на прослушивание")
    public String addFirstEpisodeToQueue() {
        episodesList.get(0).click();
        String episodeName = podcastTitle.getAttribute("text");
        optionsButton.click();
        addToQueueButton.click();
        back();
        back();

        return episodeName;
    }

    @Step("Добавление первого эпизода подкаста в избранное")
    public String addFirstEpisodeToFavorites() {
        episodesList.get(0).click();
        String episodeName = podcastTitle.getAttribute("text");
        optionsButton.click();
        addToFavoritesButton.click();
        back();

        return episodeName;
    }

    @Step("Проверка эпизода в избранных")
    public SubscriptionsPage checkEpisodeInFavorites(String episodeName) {
        filterButton.click();
        favoritesFilter.click();
        back();

        episodesTitles.shouldHave(CollectionCondition.itemWithText(episodeName));

        return this;
    }
}
