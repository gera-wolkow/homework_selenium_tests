package main.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class AnnouncementPage {

    public static final SelenideElement ADD_TO_FAVORITES_BUTTON = $("a[id=a_fav]");

    public static void addToFavorites () {
        ADD_TO_FAVORITES_BUTTON.click();
        $("div[id=alert_dv]").should(Condition.appear);
    }

    public static String getAnnouncementCompanyName () {
        return $("td[id=tdo_357]").text();
    }

    public static String getAnnouncementLocation () {
        return $("td[id=tdo_1284]").text();
    }

    public static String getAnnouncementTitle () {
        String[] lines = $("div[id=msg_div_msg]").text().split("\\r?\\n");
        return lines[0];
    }
}
