package selenideTests;

import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class Main {

    @BeforeClass
    public static void setUpBrowser () {
        open("https://www.ss.com/en");
    }

}
