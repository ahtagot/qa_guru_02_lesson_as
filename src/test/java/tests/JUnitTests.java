package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class JUnitTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.foodnetwork.com/";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setUp() {
        open("");
    }


    @DisplayName("Header should contain 7 tabs")
    @ValueSource(strings = {"Recipes", "Shows", "Chefs", "Trending", "Shop", "Sweepstakes", "What's on TV"})
    @ParameterizedTest(name = "Header should contain 7 tabs")
    void checkHeaderMenuElements(String topElements) {
        $("#mod-header-1").shouldHave(text(topElements));
    }

    @DisplayName("Hamburger menu options contains correct names and links")
    @CsvFileSource(resources = "/testdata/junitTestData.csv")
    @ParameterizedTest(name = "Hamburger menu options contains correct names and links")
    void searchResultsShouldContainExpectedName(String menuListName, String expectedURL) {
        $(".o-Header__m-NavItem ").hover();
        $(".o-Header__m-NavItem ").$(byText(menuListName)).click();
//        $(".o-Header__m-NavItem ").$(byText(menuListName)).shouldHave(text(menuListName));
//        $(".o-Header__m-DropdownListItem").click();
        String currentUrl = WebDriverRunner.url();
        Assertions.assertEquals(expectedURL, currentUrl);
    }

    public enum TimeZone {
        Eastern, Central, Mountain, Pacific
    }

    @EnumSource(TimeZone.class)
    @DisplayName("Check Tv Schedule is not empty for each timezone ")
    @ParameterizedTest(name = "Check Tv Schedule is not empty for each timezone ")
    void checkTvScheduleNotEmptyForEachTimezone(TimeZone timeZone) {
        open("shows/tv-schedule");
        $("#mod-select-button-1").$(byText(String.valueOf(timeZone))).click();
        $$(".o-ProgramSchedule__m-EpisodeCard.m-EpisodeCard.show-more").shouldBe(sizeGreaterThan(0));
    }

}
