package com.simbirsoft.test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AntennaPodTests extends TestBase {

    private final String PODCAST_TITLE = "Newscast";

    @ValueSource(strings = {"kuji podcast", "Joe Rogan Experience Review podcast", "Newscast"})
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка поиска подкаста по названию: ")
    @Severity(SeverityLevel.CRITICAL)
    void searchPodcastByTitle(String title) {
        searchPage
                .searchPodcast(title)
                .checkPodcastInResultList(title);
    }

    @Test
    @DisplayName("Добавление подкаста в подписки")
    @Severity(SeverityLevel.NORMAL)
    void addPodcastToSubscriptions() {
        searchPage
                .searchPodcast(PODCAST_TITLE)
                .addPodcastToSubscriptions(PODCAST_TITLE);

        subscriptionsPage
                .openPage()
                .checkPodcastInSubscriptionsList(PODCAST_TITLE);
    }

    @Test
    @DisplayName("Удаление подкаста из подписок")
    @Severity(SeverityLevel.NORMAL)
    void removePodcastFromSubscriptions() {
        searchPage
                .searchPodcast(PODCAST_TITLE)
                .addPodcastToSubscriptions(PODCAST_TITLE);

        subscriptionsPage
                .openPage()
                .checkPodcastInSubscriptionsList(PODCAST_TITLE)
                .openPodcast(PODCAST_TITLE)
                .openPodcastOptions()
                .removePodcast()
                .openPage()
                .checkEmptySubscriptionsList();
    }

    @Test
    @DisplayName("Переименование подкаста")
    @Severity(SeverityLevel.MINOR)
    void renamePodcast() {
        searchPage
                .searchPodcast(PODCAST_TITLE)
                .addPodcastToSubscriptions(PODCAST_TITLE);

        subscriptionsPage
                .openPage()
                .checkPodcastInSubscriptionsList(PODCAST_TITLE)
                .openPodcast(PODCAST_TITLE)
                .openPodcastOptions()
                .renamePodcast("New name");
    }

    @ValueSource(strings = {"Austria", "Armenia", "Argentina"})
    @ParameterizedTest(name = "{0}")
    @DisplayName("Проверка рекомендаций подкастов по стране: ")
    @Severity(SeverityLevel.NORMAL)
    void searchSuggestionsByCountry(String country) {
        searchPage
                .openCountrySuggestions()
                .searchSuggestionsByCountry(country);
    }

    @Test
    @DisplayName("Добавление эпизода подкаста в очередь на прослушивание")
    @Severity(SeverityLevel.CRITICAL)
    void addEpisodeToQueue() {
        searchPage
                .searchPodcast(PODCAST_TITLE)
                .addPodcastToSubscriptions(PODCAST_TITLE);

        subscriptionsPage
                .openPage()
                .openPodcast(PODCAST_TITLE);

        String episode = subscriptionsPage.addFirstEpisodeToQueue();

        queuePage
                .openPage()
                .checkEpisodeInQueue(episode);
    }

    @Test
    @DisplayName("Очистка очереди прослушивания")
    @Severity(SeverityLevel.NORMAL)
    void clearQueue() {
        searchPage
                .searchPodcast(PODCAST_TITLE)
                .addPodcastToSubscriptions(PODCAST_TITLE);

        subscriptionsPage
                .openPage()
                .openPodcast(PODCAST_TITLE);

        String episode = subscriptionsPage.addFirstEpisodeToQueue();

        queuePage
                .openPage()
                .checkEpisodeInQueue(episode)
                .clearQueue()
                .checkEmptyQueue();
    }

    @Test
    @DisplayName("Добавление эпизода в избранное")
    @Severity(SeverityLevel.MINOR)
    void addEpisodeToFavorites() {
        searchPage
                .searchPodcast(PODCAST_TITLE)
                .addPodcastToSubscriptions(PODCAST_TITLE);

        subscriptionsPage
                .openPage()
                .openPodcast(PODCAST_TITLE);

        String episode = subscriptionsPage.addFirstEpisodeToFavorites();

        subscriptionsPage.checkEpisodeInFavorites(episode);
    }

    @Test
    @DisplayName("Отключение всех уведомлений от приложения")
    @Severity(SeverityLevel.CRITICAL)
    void disableNotificationsFromApp() {
        settingsPage
                .openPage()
                .openNotificationsSettings()
                .disableAllNotifications()
                .checkDisableNotificationsMessage();
    }
}
