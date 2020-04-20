package selenideTests;

import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingSeveralAddsAsFavoriteFeature extends Main{

    @Test
    public static void checkingMemoNumber () {
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        Assert.assertFalse(announcementList.addToFavoritesButton.isDisplayed());
        announcementList.selectAnnouncement(announcementIdOne);
        announcementList.selectAnnouncement(announcementIdTwo);
        Assert.assertTrue(announcementList.addToFavoritesButton.isDisplayed());
        announcementList.addToFavoritesButton.click();
        Assert.assertEquals(" (2)", defaultPage.getFavoritesNumber());
    }

    @Test
    public static void checkingMemoList () {
        defaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertFalse(announcementList.listOfAnnouncements.isDisplayed());
        announcementList.getToAnnouncementList ();
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        announcementList.addToFavorites (announcementIdOne);
        defaultPage.confirmNotification();
        announcementList.addToFavorites (announcementIdTwo);
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        ElementsCollection list = announcementList.getListOfAnnouncements;
        int i = 0;
        while (i < list.size()) {
            Assert.assertTrue(list.get(i).getAttribute("id").equals(announcementIdOne)  | list.get(i).getAttribute("id").equals(announcementIdTwo));
            i++;
        }
    }

    @Test
    public static void checkingNotificationPopup () {
        String announcementIdOne = getRandomAdd();
        announcementList.addToFavorites (announcementIdOne);
        Assert.assertTrue(defaultPage.notificationAlert.isDisplayed());
        Assert.assertEquals(translation.get("notificationHeader"), defaultPage.getNotificationHead());
        Assert.assertEquals(translation.get("notificationBody"), defaultPage.getNotificationBody());
        defaultPage.confirmNotification();
        Assert.assertFalse(defaultPage.notificationAlert.isDisplayed());
    }
}
