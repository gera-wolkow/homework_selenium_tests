package selenideTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class test extends Main{
    public static String announcementId = "tr_47535962";

    public static String companyNameOnPage;
    public static String locationOnPage;
    public static String titleOnPage;
    public static String companyNameInList;
    public static String locationInList;
    public static String titleInList;

    @Test
    public void navigatingToAnnouncement() {
        $(By.className("main_head2")).click();
        $(By.id("ahc_14080")).click();
        $(By.id("ahc_321")).click();
        companyNameInList = $(By.id(announcementId)).$(By.className("amopt")).text();
        locationInList = $(By.id(announcementId)).$(By.className("ads_region")).text();
        $(By.id(announcementId)).click();
        companyNameOnPage = $(By.id("tdo_357")).text();
        locationOnPage = $(By.id("tdo_1284")).text();
        Assert.assertEquals(companyNameInList, companyNameOnPage);
        Assert.assertEquals(locationInList, locationOnPage);
    }
}
