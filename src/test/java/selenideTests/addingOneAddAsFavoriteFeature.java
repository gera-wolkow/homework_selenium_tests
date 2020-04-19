package selenideTests;

import org.junit.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingOneAddAsFavoriteFeature extends Main{

    @Test (priority = 0)
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

    @Test (priority = 1)
    public static void checkingMemoNumber() {
        String announcementId = getRandomAdd();
        $("tr[id=" +announcementId + "]").click();
        $("a[id=a_fav]").click();
        Assert.assertEquals(" (1)", $("span[id=mnu_fav_id]").text());
    }

    @Test (priority = 2)
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
        $("a[id=a_fav]").click();
        $("a[title=Memo]").click();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        Assert.assertTrue($("form[id=filter_frm]").isDisplayed());
        Assert.assertEquals(announcementId, $("tr[id^=tr_]").getAttribute("id"));

    }
}
