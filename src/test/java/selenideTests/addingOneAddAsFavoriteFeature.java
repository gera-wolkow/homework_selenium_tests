package selenideTests;

import org.junit.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.url;



/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingOneAddAsFavoriteFeature extends Main{

    @Test
    public static void navigatingToAnnouncement() {
        String announcementId = getRandomAdd();
        String companyNameInList = announcementList.getAnnouncementCompanyName(announcementId);
        String locationInList = announcementList.getAnnouncementLocation(announcementId);
        String titleInList = announcementList.getAnnouncementTitle(announcementId);
        announcementList.openAnnounsement(announcementId);
        Assert.assertEquals(companyNameInList, announcementPage.getAnnouncementCompanyName());
        Assert.assertEquals(locationInList, announcementPage.getAnnouncementLocation());
        Assert.assertEquals(titleInList, announcementPage.getAnnouncementTitle());
    }

    @Test
    public static void checkingMemoNumber() {
        String announcementId = getRandomAdd();
        announcementList.openAnnounsement(announcementId);
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        Assert.assertEquals(" (1)", defaultPage.getFavoritesNumber());
    }

    @Test
    public static void checkingMemoList() {
        defaultPage.goToFavorites();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertFalse(announcementList.listOfAnnouncements.isDisplayed());
        announcementList.getToAnnouncementList ();
        String announcementId = getRandomAdd();
        announcementList.openAnnounsement(announcementId);
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertTrue(announcementList.listOfAnnouncements.isDisplayed());
        Assert.assertEquals(announcementId, announcementList.getListOfAnnouncements.get(0).getAttribute("id"));
    }

    @Test
    public static void checkingAnnouncementUrl () {
        String announcementId = getRandomAdd();
        announcementList.openAnnounsement(announcementId);
        String announcementUrl = url();
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        announcementList.openAnnounsement(announcementId);
        Assert.assertEquals(announcementUrl, url());
    }

    @Test
    public static void checkingNotificationPopup () {
        String announcementId = getRandomAdd();
        announcementList.openAnnounsement(announcementId);
        announcementPage.addToFavorites();
        Assert.assertTrue(defaultPage.notificationAlert.isDisplayed());
        Assert.assertEquals("Attention", defaultPage.getNotificationHead());
        Assert.assertEquals("Advertisement added to favorites.", defaultPage.getNotificationBody());
        defaultPage.confirmNotification();
        Assert.assertFalse(defaultPage.notificationAlert.isDisplayed());
    }

    @Test
    public static void checkingAnnouncementPage () {
        String announcementId = getRandomAdd();
        announcementList.openAnnounsement(announcementId);
        Assert.assertEquals("Add to favorites", announcementPage.addToFavoritesButton.text());
    }
}
