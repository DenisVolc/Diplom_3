package login;

import constants.URL;
import http.ApiMethods;
import http.json.RegisterRequsetCard;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import tech.TechClass;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class LoginTests {
    WebDriver driver;
    ApiMethods apiMethod = new ApiMethods();
    LoginPage loginPage;
    MainPage mainPage;
    RegisterRequsetCard requestCard;
    private String accessToken;
    TechClass browser = new TechClass();
    @Before
    public void setUp(){
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        requestCard = new RegisterRequsetCard(
                "user"+ TechClass.getRandomIndex() +"@a.com",
                TechClass.getRandomIndex(),
                "user"+TechClass.getRandomIndex());
        accessToken = apiMethod.apiReg(requestCard).getBody().path("accessToken");
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void registerTest(){
        openLoginPage();
        loginPage.inputEmail(requestCard.getEmail());
        loginPage.inputPassword(requestCard.getPassword());
        loginPage.clickLoginButton();
        assertTrue(mainPage.isMakeBurgerTitleVisible());// todo нажать "Личный кабинет" и сверить email
    }


    @Step("Открыть страницу авторизации")
    public void openLoginPage(){
        driver.get(URL.LOGIN_PAGE_URL);
    }
    @After
    public void cleanUp(){
        driver.quit();
        apiMethod.apiDelete(accessToken);//удалить
    }
}
