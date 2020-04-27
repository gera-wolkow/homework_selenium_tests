package main.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by iuriiryndin on 19.04.2020
 */
public class defaultPage {

    public static SelenideElement notificationAlert = $("div[id=alert_dv]");

    public static void goToFavorites () {
        $("span[class=page_header_menu]").$$("a[class=a_menu]").get(3).click();
    }

    public static String getFavoritesNumber () {
        $("span[id=mnu_fav_id]").should(Condition.appear);
        return $("span[id=mnu_fav_id]").text();
    }

    public static void confirmNotification () {
        $("a[id=alert_ok]").click();
        notificationAlert.should(Condition.disappear);
    }

    public static void closeNotification () {
        $("div[class=alert_head_right]").click();
        notificationAlert.should(Condition.disappear);
    }

    public static String getNotificationHead () {
        return $("div[class=alert_head_left]").text();
    }

    public static String getNotificationBody () {
        return $("div[id=alert_msg]").text();
    }
}
