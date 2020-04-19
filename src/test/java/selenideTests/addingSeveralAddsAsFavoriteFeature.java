package selenideTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class addingSeveralAddsAsFavoriteFeature extends Main{

    @Test
    public static void test () {
        String announcementIdOne = getRandomAdd();
        String announcementIdTwo = getRandomAdd();
        $(By.id(announcementIdOne)).$("input[id^=c]").click();
        $(By.id(announcementIdTwo)).$("input[id^=c]").click();

    }
}
