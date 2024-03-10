package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//-----------------------------------ЛОКАТОРЫ------------------------------------------------------------
    @FindBy(xpath = "//fieldset[1]//div[1]//div[1]//input[1]")
    private WebElement nameInput; // Поле ввода email
    @FindBy(xpath = "//fieldset[2]//div[1]//div[1]//input[1]")
    private WebElement emailInput; // Поле ввода email
    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordInput; // Поле ввода email
    @FindBy(xpath = "//button[contains(text(),'Зарегистрироваться')]")
    private WebElement registerButton; // Кнопка "Зарегестрироваться"
    @FindBy(xpath = "//p[@class='input__error text_type_main-default']")
    private WebElement wrongPasswordTitle;


//-----------------------------------------------МЕТОДЫ-----------------------------------------------
    @Step("Ввести имя")
    public void nameInput(String name){
        nameInput.sendKeys(name);
    }
    @Step("Ввести email")
    public void emailInput(String email){
        emailInput.sendKeys(email);
    }
    @Step("Ввести пароль")
    public void passwordInput(String password){
        passwordInput.sendKeys(password);
    }
    @Step("Нажать кнопку \"Зарегестрироваться\"")
    public void clickRegisterButton(){
        registerButton.click();
    }
    @Step("Проверка отображения надписи \"Некорректный пароль\"")
    public boolean isWrongPasswordTitleVisible(){
        return wrongPasswordTitle.isDisplayed();
    }

}
