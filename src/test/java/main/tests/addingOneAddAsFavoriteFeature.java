package main.tests;

import main.Main;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.pages.*;

import static com.codeborne.selenide.WebDriverRunner.url;



/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingOneAddAsFavoriteFeature extends Main {

    @Test
    public void navigatingToAnnouncement() {
        String announcementId = getRandomAdd();
        String companyNameInList = announcementList.getAnnouncementCompanyName(announcementId);
        String locationInList = announcementList.getAnnouncementLocation(announcementId);
        String titleInList = announcementList.getAnnouncementTitle(announcementId);
        announcementList.openAnnouncement(announcementId);
        Assert.assertEquals(companyNameInList, announcementPage.getAnnouncementCompanyName());
        Assert.assertEquals(locationInList, announcementPage.getAnnouncementLocation());
        Assert.assertEquals(titleInList, announcementPage.getAnnouncementTitle());
    }

    @Test
    public static void checkingMemoNumber() {
        String announcementId = getRandomAdd();
        announcementList.openAnnouncement(announcementId);
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        Assert.assertEquals(" (1)", defaultPage.getFavoritesNumber());
    }

    @Test
    public static void checkingMemoList() {
        defaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertFalse(announcementList.listOfAnnouncements.isDisplayed());
        announcementList.getToAnnouncementList ();
        String announcementId = getRandomAdd();
        announcementList.openAnnouncement(announcementId);
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertTrue(announcementList.listOfAnnouncements.isDisplayed());
        org.testng.Assert.assertEquals(announcementList.getListOfAnnouncements.size(), 1);
        Assert.assertEquals(announcementId, announcementList.getListOfAnnouncements.get(0).getAttribute("id"));
    }

    @Test
    public static void checkingAnnouncementUrl () {
        String announcementId = getRandomAdd();
        announcementList.openAnnouncement(announcementId);
        String announcementUrl = url();
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        announcementList.openAnnouncement(announcementId);
        Assert.assertEquals(announcementUrl, url());
    }

    @Test
    public static void checkingNotificationPopup () {
        String announcementId = getRandomAdd();
        announcementList.openAnnouncement(announcementId);
        announcementPage.addToFavorites();
        Assert.assertTrue(defaultPage.notificationAlert.isDisplayed());
        Assert.assertEquals(translation.get("notificationHeader"), defaultPage.getNotificationHead());
        Assert.assertEquals(translation.get("notificationBody"), defaultPage.getNotificationBody());
        defaultPage.confirmNotification();
        Assert.assertFalse(defaultPage.notificationAlert.isDisplayed());
    }

    @Test
    public static void checkingAnnouncementPage () {
        String announcementId = getRandomAdd();
        announcementList.openAnnouncement(announcementId);
        Assert.assertEquals(translation.get("favoriteButton"), announcementPage.addToFavoritesButton.text());
    }
}
