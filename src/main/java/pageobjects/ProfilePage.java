package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends SuperPage{
    public ProfilePage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//p[@class='Account_text__fZAIn text text_type_main-default']")
    private WebElement keyTitle; //текст "В этом разделе вы можете изменить свои персональные данные"
    @FindBy(className = "AppHeader_header__logo__2D0X2")
    private WebElement logo; //логтип Stellar Burgers
    @FindBy(css = "header[class='AppHeader_header__X9aJA pb-4 pt-4'] li:nth-child(1) a:nth-child(1)")
    private WebElement constructorButton;
    @FindBy(xpath = "//button[contains(text(),'Выход')]")
    private WebElement exitButton;

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo(){
        logo.click();
    }
    @Step ("Нажать на кнопку \"Конструктор\"")
    public void clickConstructorButton(){
        constructorButton.click();
    }
    @Step ("Нажать на кнопку \"Выход\"")
    public void clickExitButton(){
        exitButton.click();
    }


    public boolean isKeyTitleDisplayed(){
        return keyTitle.isDisplayed();
    }
}
