package com.simbirsoft.pages;

import com.codeborne.selenide.*;
import io.appium.java_client.MobileBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SubscriptionsPage {

    private final SelenideElement
            menuButton = $(MobileBy
            .xpath("//android.widget.ImageButton[@content-desc=\"Open menu\"]")),
            openSubscriptionsPage = $(MobileBy
                    .xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[4]")),
            optionsButton = $(MobileBy
                    .xpath("//android.widget.ImageView[@content-desc=\"More options\"]")),
            removeButton = $$(MobileBy
                    .id("de.danoeh.antennapod:id/title")).findBy(Condition.text("Remove podcast")),
            emptyViewTitle = $(MobileBy
                    .id("de.danoeh.antennapod:id/emptyViewTitle")),
            emptyViewMessage = $(MobileBy
                    .id("de.danoeh.antennapod:id/emptyViewMessage"));
    private final ElementsCollection
            subscriptionsList = $$(MobileBy
            .id("de.danoeh.antennapod:id/imgvCover"));

    public void openPage() {
        menuButton.click();
        openSubscriptionsPage.click();
    }

    public void checkPodcastInSubscriptionsList(String title) {
        subscriptionsList.shouldHave(CollectionCondition
                        .anyMatch("Check podcast in subscription list", c ->
                                c.getAttribute("content-desc").equals(title)));
    }

    public void openPodcastOptions(String title) {
        subscriptionsList
                .findBy(Condition.attribute("content-desc", title)).click();
        optionsButton.click();
    }

    public void removePodcast() {
        removeButton.click();
        Selenide.confirm();
    }

    public void checkEmptySubscriptionsList() {
        emptyViewTitle.shouldHave(Condition.text("No subscriptions"));
        emptyViewMessage.shouldHave(Condition.text("To subscribe to a podcast, press the plus icon below."));
    }


}
