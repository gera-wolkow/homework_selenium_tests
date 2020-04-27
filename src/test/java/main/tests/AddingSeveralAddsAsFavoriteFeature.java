package main.tests;

import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;
import main.Main;
import main.pages.*;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class AddingSeveralAddsAsFavoriteFeature extends Main {

    @Test
    public static void checkingMemoNumber () {
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        Assert.assertFalse(AnnouncementList.ADD_TO_FAVORITES_BUTTON.isDisplayed());
        AnnouncementList.selectAnnouncement(announcementIdOne);
        AnnouncementList.selectAnnouncement(announcementIdTwo);
        Assert.assertTrue(AnnouncementList.ADD_TO_FAVORITES_BUTTON.isDisplayed());
        AnnouncementList.ADD_TO_FAVORITES_BUTTON.click();
        Assert.assertEquals(" (2)", DefaultPage.getFavoritesNumber());
    }

    @Test
    public static void checkingMemoList () {
        DefaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertFalse(AnnouncementList.LIST_OF_ANNOUNCEMENTS.isDisplayed());
        AnnouncementList.getToAnnouncementList ();
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        AnnouncementList.addToFavorites (announcementIdOne);
        DefaultPage.confirmNotification();
        AnnouncementList.addToFavorites (announcementIdTwo);
        DefaultPage.confirmNotification();
        DefaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        ElementsCollection list = AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS;
        int i = 0;
        while (i < list.size()) {
            Assert.assertTrue(list.get(i).getAttribute("id").equals(announcementIdOne)  | list.get(i).getAttribute("id").equals(announcementIdTwo));
            i++;
        }
    }

    @Test
    public static void checkingNotificationPopup () {
        String announcementIdOne = getRandomAdd();
        AnnouncementList.addToFavorites (announcementIdOne);
        Assert.assertTrue(DefaultPage.NOTIFICATION_ALERT.isDisplayed());
        Assert.assertEquals(translation.get("notificationHeader"), DefaultPage.getNotificationHead());
        Assert.assertEquals(translation.get("notificationBody"), DefaultPage.getNotificationBody());
        DefaultPage.confirmNotification();
        Assert.assertFalse(DefaultPage.NOTIFICATION_ALERT.isDisplayed());
    }
}
