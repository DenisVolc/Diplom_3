package pageobjects;

import constants.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends SuperPage {

    public MainPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//p[contains(text(),'Личный Кабинет')]")
    private WebElement personalAccount; // кнопка "Личный кабинет"

    @FindBy(className = "AppHeader_header__logo__2D0X2")
    private WebElement logo; //логтип Stellar Burgers

    @FindBy(css = ".text.text_type_main-large.mb-5.mt-10")
    private WebElement makeBurgerTitle; //надпись соберите бургер

    @FindBy(css=".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg")
    private WebElement enterAccountButton; //кнопка "войти в аккаунт"

    public boolean isMakeBurgerTitleDisplayed(){
        return  makeBurgerTitle.isDisplayed();
    }
    @Step("Нажать кнопку \"Войти в аккаунт\"")
    public void clickEnterAccountButton(){
        enterAccountButton.click();
    }
    @Step("Нажать кнопку \"Личный кабинет\"")
    public void clickPersonalAccountButton(){
        personalAccount.click();
    }

    @Step("Открыть главную страницу")
    public void openMainPage(){
        driver.get(URL.MAIN_PAGE_URL);
    }


}
