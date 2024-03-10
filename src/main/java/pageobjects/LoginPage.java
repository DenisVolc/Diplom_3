package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "Auth_link__1fOlj")
    private WebElement registerButton; // кнопка "Зарегистрироваться"

    @FindBy(xpath = "//button[contains(text(),'Войти')]")
    private WebElement loginButton; // кнопка "Войти"


    @Step("Авторизоваться")
    public void clickLoginButton(){
        loginButton.click();
    }
    @Step("Зарегестрироваться")
    public void clickRegisterButton(){
        registerButton.click();
    }

}
