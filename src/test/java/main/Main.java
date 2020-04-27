package main;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import main.pages.*;

import java.io.*;
import java.util.Properties;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by iuriiryndin on 18.04.2020
 */
public class Main {

    public static String mainUrl;
    public static String localization;
    public static JSONObject translation;


    @BeforeSuite
    public static void start () throws IOException, ParseException {
       Configuration.remote = "http://localhost:4444/wd/hub";
        Properties properties = new Properties();
        File file = new File("config.properties");
        FileInputStream fileIn = new FileInputStream(file);
        properties.load(fileIn);
        mainUrl = properties.getProperty("mainUrl");
        localization = properties.getProperty("localization");
        fileIn.close();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(new File("./src/test/resources/" + localization + ".json")));
        translation = (JSONObject) obj;
    }

    @BeforeMethod
    public static void setUpBrowser () {
        open(mainUrl + localization);
        AnnouncementList.getToAnnouncementList ();
    }

    @AfterMethod
    public static void cleanBrowser () {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static String getRandomAdd() {
        Random r = new Random();
        int random = r.nextInt(10);
        return AnnouncementList.GET_LIST_OF_ANNOUNCEMENTS.get(random).getAttribute("id");
    }

}
