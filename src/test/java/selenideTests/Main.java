package selenideTests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class Main {

    @BeforeMethod
    public static void setUpBrowser () {
        open("https://www.ss.com/en");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        $("a[title=Job\\ and\\ business]").click();
        $("a[title=Vacancies\\ \\(Staff\\ required\\)\\,\\ Announcements]").click();
        $("a[title=Administrator\\,\\ Announcements]").click();
    }

    public static String getRandomAdd() {
        Random r = new Random();
        int random = r.nextInt(10);
        return $$("tr[id^=tr_]").get(random).getAttribute("id");
    }

}
