package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "AppHeader_header__link__3D_hX")
    private WebElement personalAccount; // кнопка "Личный кабинет"

    @FindBy(className = "active")
    private WebElement stellarBurgers; // логотип StellarBurgers





}
