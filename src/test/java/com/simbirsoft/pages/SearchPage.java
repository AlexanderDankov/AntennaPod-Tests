package com.simbirsoft.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private final SelenideElement
            menuButton = $(MobileBy
            .xpath("//android.widget.ImageButton[@content-desc=\"Open menu\"]")),
            openSearchPageButton = $(MobileBy
            .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[7]")),
            searchField = $(MobileBy
                    .id("de.danoeh.antennapod:id/combinedFeedSearchEditText")),
            searchButton = $(MobileBy
                    .id("de.danoeh.antennapod:id/searchButton")),
            subscribeButton = $(MobileBy
                    .id("de.danoeh.antennapod:id/subscribeButton"));

    private final ElementsCollection
            searchResultList = $$(MobileBy
                    .id("de.danoeh.antennapod:id/txtvTitle"));


    public void searchPodcast(String title) {
        openSearchPageButton.click();
        searchField.setValue(title);
        searchButton.click();
    }

    public void checkPodcastInResultList(String title) {
        searchResultList.shouldHave(CollectionCondition.itemWithText(title));
    }

    public void addPodcastToSubscriptions(String title) {
        searchResultList.findBy(Condition.exactText(title)).click();
        subscribeButton.click();
        back();
        back();
    }
}
