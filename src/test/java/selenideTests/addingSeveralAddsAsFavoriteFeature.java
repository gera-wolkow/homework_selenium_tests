package selenideTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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
        Assert.assertFalse($("a[id=a_fav_sel]").isDisplayed());
        Assert.assertFalse($("a[id=clear_selected_a]").isDisplayed());
        $(By.id(announcementIdOne)).$("input[id^=c]").click();
        $(By.id(announcementIdTwo)).$("input[id^=c]").click();
        Assert.assertTrue($("a[id=a_fav_sel]").isDisplayed());
        Assert.assertTrue($("a[id=clear_selected_a]").isDisplayed());
        $("a[id=a_fav_sel]").click();
        $("span[id=mnu_fav_id]").shouldHave(Condition.text(" (2)"));
        org.junit.Assert.assertEquals(" (2)", $("span[id=mnu_fav_id]").text());
    }

    @Test
    public static void checkingMemoList () {
        $("a[title=Memo]").click();
        org.junit.Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        org.junit.Assert.assertFalse($("form[id=filter_frm]").isDisplayed());
        $("a[title=Объявления]").click();
        $("a[title=Job\\ and\\ business]").click();
        $("a[title=Vacancies\\ \\(Staff\\ required\\)\\,\\ Announcements]").click();
        $("a[title=Administrator\\,\\ Announcements]").click();
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        if (announcementIdOne == announcementIdTwo) {
            announcementIdTwo = getRandomAdd();
        }
        $(By.id(announcementIdOne)).$("input[id^=c]").click();
        $(By.id(announcementIdTwo)).$("input[id^=c]").click();
        $("a[id=a_fav_sel]").click();
        $("a[title=Memo]").click();
        Assert.assertEquals("https://www.ss.com/en/favorites/", url());
        ElementsCollection announcementList = $$("tr[id^=tr_]");
        int i = 0;
        while (i < announcementList.size()) {
            Assert.assertTrue(announcementList.get(i).getAttribute("id").equals(announcementIdOne)  | announcementList.get(i).getAttribute("id").equals(announcementIdTwo));
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
        $(By.id(announcementIdOne)).$("input[id^=c]").click();
        $(By.id(announcementIdTwo)).$("input[id^=c]").click();
        $("a[id=a_fav_sel]").click();
        $("div[id=alert_dv]").should(Condition.appear);
        org.junit.Assert.assertTrue($("div[id=alert_dv]").isDisplayed());
        org.junit.Assert.assertEquals("Attention", $("div[class=alert_head_left]").text());
        org.junit.Assert.assertEquals("Advertisement added to favorites.", $("div[id=alert_msg]").text());
        $("a[id=alert_ok]").click();
        $("div[id=alert_dv]").should(Condition.disappear);
        org.junit.Assert.assertFalse($("div[id=alert_dv]").isDisplayed());
    }
}
