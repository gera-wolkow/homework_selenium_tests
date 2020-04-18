package selenideTests;

import com.codeborne.selenide.Selenide;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingOneAddAsFavoriteFeature extends Main{

    @Test (priority = 0)
    public void navigatingToAnnouncement() {
        String announcementId = getRandomAdd();
        String companyNameInList = $(By.id(announcementId)).$(By.className("amopt")).text();
        String locationInList = $(By.id(announcementId)).$(By.className("ads_region")).text();
        String titleInList = $(By.id(announcementId)).$("a[id^=dm_]").text();
        $(By.id(announcementId)).click();
        String companyNameOnPage = $(By.id("tdo_357")).text();
        String locationOnPage = $(By.id("tdo_1284")).text();
        String[] lines = $(By.id("msg_div_msg")).text().split("\\r?\\n");
        String titleOnPage = lines[0];
        Assert.assertEquals(companyNameInList, companyNameOnPage);
        Assert.assertEquals(locationInList, locationOnPage);
        Assert.assertEquals(titleInList, titleOnPage);
    }

    @Test (priority = 1)
    public void checkingMemoNumber() {
        String announcementId = getRandomAdd();
        $(By.id(announcementId)).click();
        $(By.id("a_fav")).click();
        Assert.assertEquals(" (1)", $(By.id("mnu_fav_id")).text());
    }

    @Test (priority = 2)
    public void checkingMemoList() {
        String announcementId = getRandomAdd();
        $(By.id(announcementId)).click();
        $(By.id("a_fav")).click();
        $(By.className("a_menu")).click();
        Assert.assertEquals(" (1)", $(By.id("mnu_fav_id")).text());
    }
}
