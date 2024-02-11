import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FullTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillAllFields() {
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
        $("#uploadPicture").uploadFile(new File("src/test/resources/landscape.jpg"));
        sleep(1000);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("September")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1998")).click();
        $(".react-datepicker__day.react-datepicker__day--013").click();
        sleep(1000);
        //String dateValue = $("#dateOfBirthInput").getValue();  expirementing with taking value from date field
        //System.out.println("Name value: " + dateValue);
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();
        $("#submit").click();
        $(".modal-open").shouldBe(visible);
        $(".modal-content").shouldHave(text("John Smith"), text("j.smith@gmail.com"), text("0123456789"), text("John Smith"), text("Address Somewhere"), text("Male"), text("Chemistry"), text("Sports, Reading, Music"), text("landscape.jpg"), text("NCR Delhi"), text("13 September,1998"));


    }


}



