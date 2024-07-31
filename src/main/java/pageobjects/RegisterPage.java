package pageobjects;

import constants.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends SuperPage{

    public RegisterPage(WebDriver driver){
        super(driver);
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
    @Step("Открыть страницу регистрации")
    public void openRegisterPage(){
        driver.get(URL.REGISTER_PAGE_URL);
    }
    public void doRegister(String name, String email,String password){
        nameInput(name);
        emailInput(email);
        passwordInput(password);
        clickRegisterButton();
    }
}
