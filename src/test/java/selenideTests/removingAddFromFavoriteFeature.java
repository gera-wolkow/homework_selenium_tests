package selenideTests;

import org.junit.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class removingAddFromFavoriteFeature extends Main{

    @Test
    public static void removingSingleAnnouncement () {
        String announcementId = getRandomAdd();
        announcementList.openAnnounsement(announcementId);
        announcementPage.addToFavorites();
        defaultPage.confirmNotification();
        defaultPage.goToFavorites();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertTrue(announcementList.listOfAnnouncements.isDisplayed());
        announcementList.removeFromFavorites(announcementId);
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertFalse(announcementList.listOfAnnouncements.isDisplayed());
    }



}
