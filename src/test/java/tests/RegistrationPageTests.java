package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationPageTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void registrationPageTestAllFieldsFilled() {

        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Smith")
                .setUserEmail("j.smith@gmail.com")
                .setGender("Male")
                .setUserPhoneNumber("0123456789")
                .setUserCurrentAddress("Nowhere")
                .setDateOfBirth("30", "July", "2008")
                .setUserSubjects("Chemistry")
                .setHobbies("Sports")
                .uploadPicture("landscape.jpg")
                .selectState("NCR")
                .selectCity("Delhi")
                .clickSubmitButton()

                // Results verification
                .checkResult("Student Name", "John Smith")
                .checkResult("Student Email", "j.smith@gmail.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "0123456789")
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Subjects", "Chemistry")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "landscape.jpg")
                .checkResult("Address", "Nowhere")
                .checkResult("State and City", "NCR Delhi");

    }

    @Test
    void registrationPageTestRequiredFieldsOnly() {

        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Smith")
                .setGender("Male")
                .setUserPhoneNumber("0123456789")

                .clickSubmitButton()

                // Results verification
                .checkResult("Student Name", "John Smith")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "0123456789");

    }

    @Test
    void registrationPageNegativeTest() {
        registrationPage.openPage()
                .setFirstName("")
                .setLastName("Smith")
                .setGender("Male")
                .setUserPhoneNumber("0123456789")
                .clickSubmitButton()

                // Results verification
                .checkResultTableIsNotVisible();

    }
}
