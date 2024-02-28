package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxPageTests  extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void textBoxPageTestAllFieldsFilled(){

        textBoxPage.openPage()
                .setFullName("John Smith")
                .setUserEmail("j.smith@gmail.com")
                .setUserCurrentAddress("Nowhere")
                .setUserPermanentAddress("Somewhere")
                .clickSubmitButton()

                // Results verification
                .checkResult("Name:","John Smith")
                .checkResult("Email:","j.smith@gmail.com")
                .checkResult("Current Address :","Nowhere")
                .checkResult("Permananet Address :","Somewhere");

    }


}
