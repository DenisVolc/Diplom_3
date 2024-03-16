package pageobjects;

import constants.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPage extends SuperPage {
    //https://stellarburgers.nomoreparties.site/forgot-password

    public ForgotPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "Auth_link__1fOlj")
    private WebElement loginButton;
    @Step("Нажать кнопку \"Войти\"")
    public void clickLoginButton(){
        loginButton.click();
    }
    @Step("Открыть страницу восстановления пароля")
    public void openForgotPage(){
        driver.get(URL.FORGOT_PASSWORD_PAGE_URL);
    }

}
