package main.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class AnnouncementList {

    public static final SelenideElement SHOW_SELECTED_BUTTON = $("a[id=show_selected_a]");
    public static final SelenideElement ADD_TO_FAVORITES_BUTTON = $("a[id=a_fav_sel]");
    public static final SelenideElement CLEAR_SELECTED_BUTTON = $("a[id=clear_selected_a]");
    public static final SelenideElement REMOVE_FROM_FAVORITES_BUTTON = $("a[id=del_selected_a]");

    public static final SelenideElement LIST_OF_ANNOUNCEMENTS = $("form[id=filter_frm]");

    public static final ElementsCollection GET_LIST_OF_ANNOUNCEMENTS = $$("tr[id^=tr_]");

    public static void getToAnnouncementList () {
        $("span[class=page_header_head]").click();
        $("a[id=mtd_14080]").click();
        $("a[id=ahc_321]").click();
    }

    public static void openAnnouncement(String id) {
        $("tr[id=" + id + "]").click();
    }

    public static void selectAnnouncement(String id) {
        $("tr[id=" + id + "]").$("input[id^=c]").click();
    }

    public static void addToFavorites (String id) {
        $("tr[id=" + id + "]").$("input[id^=c]").click();
        ADD_TO_FAVORITES_BUTTON.should(Condition.appear);
        ADD_TO_FAVORITES_BUTTON.click();
        $("div[id=alert_dv]").should(Condition.appear);
    }

    public static void removeFromFavorites (String id) {
        $("tr[id=" + id + "]").$("input[id^=c]").click();
        REMOVE_FROM_FAVORITES_BUTTON.should(Condition.appear);
        REMOVE_FROM_FAVORITES_BUTTON.click();
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
