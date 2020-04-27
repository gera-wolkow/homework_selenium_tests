package main.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import main.Main;
import main.pages.*;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class RemovingAddFromFavoriteFeature extends Main {

    @Test
    public static void removingSingleAnnouncement () {
        String announcementId = getRandomAdd();
        AnnouncementList.openAnnouncement(announcementId);
        AnnouncementPage.addToFavorites();
        DefaultPage.confirmNotification();
        DefaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertTrue(AnnouncementList.LIST_OF_ANNOUNCEMENTS.isDisplayed());
        AnnouncementList.removeFromFavorites(announcementId);
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertFalse(AnnouncementList.LIST_OF_ANNOUNCEMENTS.isDisplayed());
    }

    @Test
    public static void removingOneOfSeveralAnnouncements () {
        String announcementIdFirst = getRandomAdd();
        String announcementIdSecond = getRandomAdd();
        if (announcementIdFirst == announcementIdSecond) {
            announcementIdSecond = getRandomAdd();
        }
        AnnouncementList.addToFavorites (announcementIdFirst);
        AnnouncementList.addToFavorites (announcementIdSecond);
        Assert.assertEquals(DefaultPage.getFavoritesNumber(), " (2)");
        DefaultPage.goToFavorites();
        Assert.assertTrue(AnnouncementList.LIST_OF_ANNOUNCEMENTS.isDisplayed());
        Assert.assertEquals(AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS.size(), 2);
        AnnouncementList.removeFromFavorites(announcementIdFirst);
        Assert.assertEquals(DefaultPage.getFavoritesNumber(), " (1)");
        Assert.assertEquals(AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS.size(), 1);
        Assert.assertEquals(AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS.get(0).getAttribute("id"), announcementIdSecond);
    }

}
