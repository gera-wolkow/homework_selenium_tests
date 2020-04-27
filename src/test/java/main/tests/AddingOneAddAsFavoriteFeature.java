package main.tests;

import main.Main;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.pages.*;

import static com.codeborne.selenide.WebDriverRunner.url;



/**
 * Created by iuriiryndin on 18.04.2020
 */
public class AddingOneAddAsFavoriteFeature extends Main {

    @Test
    public void navigatingToAnnouncement() {
        String announcementId = getRandomAdd();
        String companyNameInList = AnnouncementList.getAnnouncementCompanyName(announcementId);
        String locationInList = AnnouncementList.getAnnouncementLocation(announcementId);
        String titleInList = AnnouncementList.getAnnouncementTitle(announcementId);
        AnnouncementList.openAnnouncement(announcementId);
        Assert.assertEquals(companyNameInList, AnnouncementPage.getAnnouncementCompanyName());
        Assert.assertEquals(locationInList, AnnouncementPage.getAnnouncementLocation());
        Assert.assertEquals(titleInList, AnnouncementPage.getAnnouncementTitle());
    }

    @Test
    public static void checkingMemoNumber() {
        String announcementId = getRandomAdd();
        AnnouncementList.openAnnouncement(announcementId);
        AnnouncementPage.addToFavorites();
        DefaultPage.confirmNotification();
        Assert.assertEquals(" (1)", DefaultPage.getFavoritesNumber());
    }

    @Test
    public static void checkingMemoList() {
        DefaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertFalse(AnnouncementList.LIST_OF_ANNOUNCEMENTS.isDisplayed());
        AnnouncementList.getToAnnouncementList ();
        String announcementId = getRandomAdd();
        AnnouncementList.openAnnouncement(announcementId);
        AnnouncementPage.addToFavorites();
        DefaultPage.confirmNotification();
        DefaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertTrue(AnnouncementList.LIST_OF_ANNOUNCEMENTS.isDisplayed());
        org.testng.Assert.assertEquals(AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS.size(), 1);
        Assert.assertEquals(announcementId, AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS.get(0).getAttribute("id"));
    }

    @Test
    public static void checkingAnnouncementUrl () {
        String announcementId = getRandomAdd();
        AnnouncementList.openAnnouncement(announcementId);
        String announcementUrl = url();
        AnnouncementPage.addToFavorites();
        DefaultPage.confirmNotification();
        DefaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        AnnouncementList.openAnnouncement(announcementId);
        Assert.assertEquals(announcementUrl, url());
    }

    @Test
    public static void checkingNotificationPopup () {
        String announcementId = getRandomAdd();
        AnnouncementList.openAnnouncement(announcementId);
        AnnouncementPage.addToFavorites();
        Assert.assertTrue(DefaultPage.NOTIFICATION_ALERT.isDisplayed());
        Assert.assertEquals(translation.get("notificationHeader"), DefaultPage.getNotificationHead());
        Assert.assertEquals(translation.get("notificationBody"), DefaultPage.getNotificationBody());
        DefaultPage.confirmNotification();
        Assert.assertFalse(DefaultPage.NOTIFICATION_ALERT.isDisplayed());
    }

    @Test
    public static void checkingAnnouncementPage () {
        String announcementId = getRandomAdd();
        AnnouncementList.openAnnouncement(announcementId);
        Assert.assertEquals(translation.get("favoriteButton"), AnnouncementPage.ADD_TO_FAVORITES_BUTTON.text());
    }
}
