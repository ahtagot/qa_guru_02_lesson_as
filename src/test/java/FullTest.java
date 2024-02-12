import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FullTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 5000;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillAllFields() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");


        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("j.smith@gmail.com");
        $("#userNumber").setValue("0123456789");
        $("#currentAddress").setValue("Address Somewhere");
        $(byText("Male")).click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbies-checkbox-1").parent().click();
        $("#hobbies-checkbox-2").parent().click();
        $("#hobbies-checkbox-3").parent().click();
        $("#uploadPicture").uploadFromClasspath("landscape.jpg");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("September")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1998")).click();
        $(".react-datepicker__day.react-datepicker__day--013").click();
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();
        $(".modal-open").shouldBe(visible);
        $(".modal-content").shouldHave(text("John Smith"));
        $(".modal-content").shouldHave(text("j.smith@gmail.com"));
        $(".modal-content").shouldHave(text("0123456789"));
        $(".modal-content").shouldHave(text("Male"));
        $(".modal-content").shouldHave(text("Chemistry"));
        $(".modal-content").shouldHave(text("Sports, Reading, Music"));
        $(".modal-content").shouldHave(text("landscape.jpg"));
        $(".modal-content").shouldHave(text("NCR Delhi"));
        $(".modal-content").shouldHave(text("13 September,1998"));




    }


}



