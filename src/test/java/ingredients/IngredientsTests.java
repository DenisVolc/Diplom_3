package ingredients;

import http.client.DeleteApi;
import http.client.PostApi;
import http.json.RegisterRequsetCard;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.ProfilePage;
import tech.TechClass;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class IngredientsTests {
    WebDriver driver;
    MainPage mainPage;
    TechClass browser = new TechClass();

    @Before
    public void setUp() {
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }
    @Test
    @DisplayName("Проверка перехода к разделу \"Булки\"")
    public void bunTransitionTest(){
        mainPage.clickSauceTitle();
        mainPage.clickBunTitle();
        assertTrue(mainPage.isBunSelected());
    }
    @Test
    @DisplayName("Проверка перехода к разделу \"Соусы\"")
    public void sauceTransitionTest(){
        mainPage.clickSauceTitle();
        assertTrue(mainPage.isSauceSelected());
    }
    @Test
    @DisplayName("Проверка перехода к разделу \"Соусы\"")
    public void fillingsTransitionTest(){
        mainPage.clickFillingsTitle();
        assertTrue(mainPage.isFillingsSelected());
    }
    @After
    public void cleanUp(){
        driver.quit();
    }
}
