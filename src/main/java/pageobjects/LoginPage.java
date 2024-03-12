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
    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailInput; // поле ввода email
    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordInput; // поле ввода email

    @Step("Авторизоваться")
    public void clickLoginButton(){
        loginButton.click();
    }
    @Step("Зарегестрироваться")
    public void clickRegisterButton(){
        registerButton.click();
    }
    @Step("Ввести email")
    public void inputEmail(String email){
        emailInput.sendKeys(email);
    }
    @Step("Ввести пароль")
    public void inputPassword(String password){
        passwordInput.sendKeys(password);
    }
}
