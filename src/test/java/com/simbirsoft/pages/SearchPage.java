package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private final SelenideElement
            openSearchPageButton = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle"))
                    .findBy(Condition.text("Add Podcast")),
            searchField = $(MobileBy.id("de.danoeh.antennapod:id/combinedFeedSearchEditText")),
            searchButton = $(MobileBy.id("de.danoeh.antennapod:id/searchButton")),
            subscribeButton = $(MobileBy.id("de.danoeh.antennapod:id/subscribeButton")),
            discoverButton = $(MobileBy.id("de.danoeh.antennapod:id/discover_more")),
            emptySuggestionsMessage = $(MobileBy.id("de.danoeh.antennapod:id/txtvError")),
            selectCountryOptions = $(MobileBy.id("de.danoeh.antennapod:id/spinner_country"));

    private final ElementsCollection
            searchResultList = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle")),
            countriesList = $$(MobileBy.id("android:id/text1")),
            suggestionsList = $$(MobileBy.id("de.danoeh.antennapod:id/txtvTitle"));


    @Step("Поиск подкаста по названию")
    public SearchPage searchPodcast(String title) {
        openSearchPageButton.click();
        searchField.setValue(title);
        searchButton.click();

        return this;
    }

    @Step("Проверка подкаста по названию в результатах выдачи")
    public SearchPage checkPodcastInResultList(String title) {
        searchResultList.shouldHave(CollectionCondition.itemWithText(title));

        return this;
    }

    @Step("Добавление подкаста в подписки")
    public SearchPage addPodcastToSubscriptions(String title) {
        searchResultList.findBy(Condition.exactText(title)).click();
        subscribeButton.click();
        back();
        back();

        return this;
    }

    @Step("Открытие страницы с рекомендациями по странам")
    public SearchPage openCountrySuggestions() {
        openSearchPageButton.click();
        discoverButton.click();

        return this;
    }

    @Step("Поиск рекомендаций по странам")
    public SearchPage searchSuggestionsByCountry(String country) {
        emptySuggestionsMessage.shouldBe(Condition.visible);
        emptySuggestionsMessage.shouldHave(Condition.text("You selected to hide suggestions."));

        selectCountryOptions.click();
        countriesList.findBy(Condition.text(country)).click();

        emptySuggestionsMessage.shouldNotBe(Condition.visible);
        suggestionsList.shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }
}
