package selenideTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideElementListIterator;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class announcementList {

    public static SelenideElement showSelectedButton = $("a[id=show_selected_a]");
    public static SelenideElement addToFavoritesButton = $("a[id=a_fav_sel]");
    public static SelenideElement clearSelectedButton = $("a[id=clear_selected_a]");
    public static SelenideElement removeFromFavoritesButton = $("a[id=del_selected_a]");

    public static SelenideElement listOfAnnouncements = $("form[id=filter_frm]");

    public static ElementsCollection getListOfAnnouncements = $$("tr[id^=tr_]");

    public static void getToAnnouncementList () {
        $("a[title=Объявления]").click();
        $("a[title=Job\\ and\\ business]").click();
        $("a[title=Vacancies\\ \\(Staff\\ required\\)\\,\\ Announcements]").click();
        $("a[title=Administrator\\,\\ Announcements]").click();
    }

    public static void openAnnounsement (String id) {
        $("tr[id=" + id + "]").click();
    }

    public static void addToFavorites (String id) {
        $("tr[id=" + id + "]").$("input[id^=c]").click();
        $("a[id=a_fav_sel]").should(Condition.appear);
        $("a[id=a_fav_sel]").click();
        $("div[id=alert_dv]").should(Condition.appear);
    }

    public static void removeFromFavorites (String id) {
        $("tr[id=" + id + "]").$("input[id^=c]").click();
        $("a[id=del_selected_a]").should(Condition.appear);
        $("a[id=del_selected_a]").click();
    }

    public static String getAnnouncementCompanyName (String id) {
        return $("tr[id=" +id + "]").$("a[class=amopt]").text();
    }

    public static String getAnnouncementLocation (String id) {
        return $("tr[id=" +id + "]").$("div[class=ads_region]").text();
    }

    public static String getAnnouncementTitle (String id) {
        return $("tr[id=" +id + "]").$("a[id^=dm_]").text();
    }
}
