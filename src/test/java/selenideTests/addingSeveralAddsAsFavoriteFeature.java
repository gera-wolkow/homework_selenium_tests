package selenideTests;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
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
        $(By.id(announcementIdOne)).$("input[id^=c]").click();
        $(By.id(announcementIdTwo)).$("input[id^=c]").click();
        Assert.assertTrue(announcementList.addToFavoritesButton.isDisplayed());
        $("a[id=a_fav_sel]").click();
        Assert.assertEquals(" (2)", defaultPage.getFavoritesNumber());
    }

    @Test
    public static void checkingMemoList () {
        defaultPage.goToFavorites();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertFalse(announcementList.listOfAnnouncements.isDisplayed());
        announcementList.getToAnnouncementList ();
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        announcementList.addToFavorites (announcementIdOne);
        announcementList.addToFavorites (announcementIdTwo);
        defaultPage.goToFavorites();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
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
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        announcementList.addToFavorites (announcementIdOne);
        announcementList.addToFavorites (announcementIdTwo);
        Assert.assertTrue(defaultPage.notificationAlert.isDisplayed());
        Assert.assertEquals("Attention", defaultPage.getNotificationHead());
        Assert.assertEquals("Advertisement added to favorites.", defaultPage.getNotificationBody());
        defaultPage.confirmNotification();
        Assert.assertFalse(defaultPage.notificationAlert.isDisplayed());
    }
}
