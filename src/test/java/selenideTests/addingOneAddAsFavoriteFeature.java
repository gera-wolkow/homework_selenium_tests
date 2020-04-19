package selenideTests;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingOneAddAsFavoriteFeature extends Main{

    @Test
    public static void navigatingToAnnouncement() {
        String announcementId = getRandomAdd();
        String companyNameInList = $("tr[id=" +announcementId + "]").$("a[class=amopt]").text();
        String locationInList = $("tr[id=" +announcementId + "]").$("div[class=ads_region]").text();
        String titleInList = $("tr[id=" +announcementId + "]").$("a[id^=dm_]").text();
        $("tr[id=" +announcementId + "]").click();
        String companyNameOnPage = $("td[id=tdo_357]").text();
        String locationOnPage = $("td[id=tdo_1284]").text();
        String[] lines = $("div[id=msg_div_msg]").text().split("\\r?\\n");
        String titleOnPage = lines[0];
        Assert.assertEquals(companyNameInList, companyNameOnPage);
        Assert.assertEquals(locationInList, locationOnPage);
        Assert.assertEquals(titleInList, titleOnPage);
    }

    @Test
    public static void checkingMemoNumber() {
        String announcementId = getRandomAdd();
        $("tr[id=" +announcementId + "]").click();
        $("a[title=Add\\ to\\ favorites]").click();
        $("a[id=alert_ok]").click();
        $("span[id=mnu_fav_id]").shouldHave(Condition.text(" (1)"));
        Assert.assertEquals(" (1)", $("span[id=mnu_fav_id]").text());
    }

    @Test
    public static void checkingMemoList() {
        $("a[title=Memo]").click();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertFalse($("form[id=filter_frm]").isDisplayed());
        $("a[title=Объявления]").click();
        $("a[title=Job\\ and\\ business]").click();
        $("a[title=Vacancies\\ \\(Staff\\ required\\)\\,\\ Announcements]").click();
        $("a[title=Administrator\\,\\ Announcements]").click();
        String announcementId = getRandomAdd();
        $("tr[id=" +announcementId + "]").click();
        $("a[title=Add\\ to\\ favorites]").click();
        $("a[id=alert_ok]").click();
        $("a[title=Memo]").click();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertTrue($("form[id=filter_frm]").isDisplayed());
        Assert.assertEquals(announcementId, $("tr[id^=tr_]").getAttribute("id"));
    }

    @Test
    public static void checkingAnnouncementUrl () {
        String announcementId = getRandomAdd();
        $("tr[id=" +announcementId + "]").click();
        String announcementUrl = url();
        $("a[title=Add\\ to\\ favorites]").click();
        $("a[id=alert_ok]").click();
        $("a[title=Memo]").click();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        $("tr[id=" +announcementId + "]").click();
        Assert.assertEquals(announcementUrl, url());
    }

    @Test
    public static void checkingNotificationPopup () {
        String announcementId = getRandomAdd();
        $("tr[id=" +announcementId + "]").click();
        $("a[title=Add\\ to\\ favorites]").click();
        $("div[id=alert_dv]").should(Condition.appear);
        Assert.assertTrue($("div[id=alert_dv]").isDisplayed());
        Assert.assertEquals("Attention", $("div[class=alert_head_left]").text());
        Assert.assertEquals("Advertisement added to favorites.", $("div[id=alert_msg]").text());
        $("a[id=alert_ok]").click();
        $("div[id=alert_dv]").should(Condition.disappear);
        Assert.assertFalse($("div[id=alert_dv]").isDisplayed());
    }

    @Test
    public static void checkingAnnouncementPage () {
        String announcementId = getRandomAdd();
        $("tr[id=" +announcementId + "]").click();
        Assert.assertEquals("Add to favorites", $("a[title=Add\\ to\\ favorites]").text());
    }
}
