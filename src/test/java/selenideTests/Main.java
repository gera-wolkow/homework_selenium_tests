package selenideTests;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class Main {
    public static String announcementId;

    @BeforeMethod
    public static void setUpBrowser () {
        open("https://www.ss.com/en");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        $(By.className("main_head2")).click();
        $(By.id("ahc_14080")).click();
        $(By.id("ahc_321")).click();
    }

    public static String getRandomAdd() {
        Random r = new Random();
        int random = r.nextInt(10);
        return $$("tr[id^=tr_]").get(random).getAttribute("id");
    }

}
