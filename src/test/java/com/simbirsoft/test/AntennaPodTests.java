package com.simbirsoft.test;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class AntennaPodTests extends TestBase {

    /**
     * Main screen:
     * Menu button:
     * //android.widget.ImageButton[@content-desc="Open menu"]
     *
     * Queue:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]
     * de.danoeh.antennapod:id/imgvCover
     * de.danoeh.antennapod:id/txtvTitle
     *
     * Inbox:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]
     *
     * Episodes:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]
     *
     * Subscriptions:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[4]
     *
     * Downloads:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[5]
     *
     * Playback history:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[6]
     *
     * Add podcast:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[7]
     *
     * Settings:
     * de.danoeh.antennapod:id/nav_settings
     *
     * Add podcast screen:
     * Search field:
     * de.danoeh.antennapod:id/combinedFeedSearchEditText
     *
     * Search button:
     * de.danoeh.antennapod:id/searchButton
     *
     * Search results screen:
     * Elements:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.GridView/android.widget.RelativeLayout[1]
     *
     * Podcast title:
     * de.danoeh.antennapod:id/txtvTitle
     *
     * Add podcast screen:
     * Subscribe button:
     * de.danoeh.antennapod:id/subscribeButton
     *
     * Back button:
     * /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageButton
     *
     *Subscribtions grid:
     * de.danoeh.antennapod:id/subscriptions_grid
     *
     *
     *
     */

    @Test
    void searchPodcastByTitle() {
        searchPage.searchPodcast("Global News Podcast");
        searchPage.checkPodcastInResultList("Global News Podcast");

    }

    @Test
    void addPodcastToSubscriptions() {
        searchPage.searchPodcast("Global News Podcast");
        searchPage.addPodcastToSubscriptions("Global News Podcast");

        subscriptionsPage.openPage();
        subscriptionsPage.checkPodcastInSubscriptionsList("Global News Podcast");
    }

    @Test
    void removePodcastFromSubscriptions() {
        searchPage.searchPodcast("Global News Podcast");
        searchPage.addPodcastToSubscriptions("Global News Podcast");

        subscriptionsPage.openPage();
        subscriptionsPage.checkPodcastInSubscriptionsList("Global News Podcast");

        subscriptionsPage.openPodcastOptions("Global News Podcast");
        subscriptionsPage.removePodcast();
        subscriptionsPage.openPage();
        subscriptionsPage.checkEmptySubscriptionsList();
    }
}
