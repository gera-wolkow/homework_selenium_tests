package selenideTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class removingAddFromFavoriteFeature extends Main{

    @Test
    public static void removingSingleAnnouncement () {
        String announcementId = getRandomAdd();
        announcementList.openAnnouncement(announcementId);
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertTrue(announcementList.listOfAnnouncements.isDisplayed());
        announcementList.removeFromFavorites(announcementId);
        Assert.assertEquals(url(), mainUrl + localization + "/favorites/");
        Assert.assertFalse(announcementList.listOfAnnouncements.isDisplayed());
    }

    @Test
    public static void removingOneOfSeveralAnnouncements () {
        String announcementIdFirst = getRandomAdd();
        String announcementIdSecond = getRandomAdd();
        if (announcementIdFirst == announcementIdSecond) {
            announcementIdSecond = getRandomAdd();
        }
        announcementList.addToFavorites (announcementIdFirst);
        announcementList.addToFavorites (announcementIdSecond);
        Assert.assertEquals(defaultPage.getFavoritesNumber(), " (2)");
        defaultPage.goToFavorites();
        Assert.assertTrue(announcementList.listOfAnnouncements.isDisplayed());
        Assert.assertEquals(announcementList.getListOfAnnouncements.size(), 2);
        announcementList.removeFromFavorites(announcementIdFirst);
        Assert.assertEquals(defaultPage.getFavoritesNumber(), " (1)");
        Assert.assertEquals(announcementList.getListOfAnnouncements.size(), 1);
        Assert.assertEquals(announcementList.getListOfAnnouncements.get(0).getAttribute("id"), announcementIdSecond);
    }

}
