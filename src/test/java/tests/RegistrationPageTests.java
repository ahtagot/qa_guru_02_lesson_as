package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static data.TestData.*;

public class RegistrationPageTests extends TestBase  {

    RegistrationPage registrationPage = new RegistrationPage();


    String firstName = generateFirstName();
    String lastName = generateLastName();
    String emailAddress = generateUserEmail();
    String phoneNumber = generatePhoneNumber();
    String userAddress = generateUserAddress();
    String gender = generateGender();
    String userDayOfBirth = generateDayOfBirth();
    String userMonthOfBirth = generateMonthOfBirth();
    String userYearOfBirth = generateUserYearOfBirth();
    String hobbie = generateUserHobbies();
    String subject = generateSubject();
    String pictureName = generateUserPicture();
    String state = generateState();
    String city = generateCity();
    @Test
    void registrationPageTestAllFieldsFilled() {


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(emailAddress)
                .setGender(gender)
                .setUserPhoneNumber(phoneNumber)
                .setUserCurrentAddress(userAddress)
                .setDateOfBirth(userDayOfBirth, userMonthOfBirth, userYearOfBirth)
                .setUserSubjects(subject)
                .setHobbies(hobbie)
                .uploadPicture(pictureName)
                .selectState(state)
                .selectCity(city)
                .clickSubmitButton()

                // Results verification
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", emailAddress)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", userDayOfBirth + " " + userMonthOfBirth + "," + userYearOfBirth)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbie)
                .checkResult("Picture", pictureName)
                .checkResult("Address", userAddress)
                .checkResult("State and City", state + " " + city);

    }

    @Test
    void registrationPageTestRequiredFieldsOnly() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserPhoneNumber(phoneNumber)

                .clickSubmitButton()

                // Results verification
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);

    }

    @Test
    void registrationPageNegativeTest() {
        registrationPage.openPage()
                .setFirstName("")
                .setLastName(lastName)
                .setGender(gender)
                .setUserPhoneNumber(phoneNumber)
                .clickSubmitButton()

                // Results verification
                .checkResultTableIsNotVisible();

    }
}
