package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.TextBoxComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    private final SelenideElement userFullNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");

    TextBoxComponent textBoxComponent = new TextBoxComponent();

    public TextBoxPage openPage() {
        open("/text-box");
        $("#app").shouldHave(text("Text Box"));
        return this;
    }

    public TextBoxPage setFullName(String userName) {
        userFullNameInput.setValue(userName);
        return this;
    }

    public TextBoxPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public TextBoxPage setUserCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setUserPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkResult(String key, String value) {
        textBoxComponent.getTextBoxResult(key, value);
        return this;
    }
}




