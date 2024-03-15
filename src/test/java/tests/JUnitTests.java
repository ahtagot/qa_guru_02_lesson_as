package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.Timezones.UsTimezones;


public class JUnitTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.foodnetwork.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {

        open("");
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }


    @DisplayName("Header should contain 7 tabs:")
    @ValueSource(strings = {"Recipes", "Shows", "Chefs", "Trending", "Shop", "Sweepstakes", "What's on TV"})
    @ParameterizedTest(name = "Header should contain 7 tabs {0}")
    void checkHeaderMenuElements(String topElements) {
        $("#mod-header-1").shouldHave(text(topElements));
    }

    @DisplayName("Hamburger menu options contains correct names and links")
    @CsvFileSource(resources = "/testdata/junitTestData.csv")
    @ParameterizedTest(name = "name: {0} and link: {1}")
    void searchResultsShouldContainExpectedName(String menuListName, String expectedURL) {
        $(".o-Header__m-NavItem ").hover();
        $(".o-Header__m-NavItem ").$(byText(menuListName)).click();
        String currentUrl = WebDriverRunner.url();
        Assertions.assertEquals(expectedURL, currentUrl);
    }


    @EnumSource(UsTimezones.class)
    @DisplayName("Check Tv Schedule is not empty for each timezone ")
    @ParameterizedTest(name = "Check Tv Schedule is not empty for each timezone {0}")
    void checkTvScheduleNotEmptyForEachTimezone(UsTimezones timeZone) {
        open("shows/tv-schedule");
        $(".m-TimeZone__a-Button").$(byText(String.valueOf(timeZone))).click();

    }

}
