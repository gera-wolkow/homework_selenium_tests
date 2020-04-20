package selenideTests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
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
        announcementList.getToAnnouncementList ();
    }

    @AfterMethod
    public static void cleanBrowser () {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static String getRandomAdd() {
        Random r = new Random();
        int random = r.nextInt(10);
        return announcementList.getListOfAnnouncements.get(random).getAttribute("id");
    }

}
