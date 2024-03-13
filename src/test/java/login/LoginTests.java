package login;

import http.client.DeleteApi;
import http.client.GetApi;
import http.client.PostApi;
import http.json.RegisterRequsetCard;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.ForgotPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import tech.TechClass;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginTests {
    WebDriver driver;
    DeleteApi deleteApi = new DeleteApi();
    GetApi getApi = new GetApi();
    PostApi postApi = new PostApi();
    LoginPage loginPage;
    MainPage mainPage;
    ForgotPage forgotPage;
    RegisterRequsetCard registerCard;
    private String accessToken;
    TechClass browser = new TechClass();
    @Before
    public void setUp(){
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        forgotPage = new ForgotPage(driver);

        registerCard = new RegisterRequsetCard(
                "testUser"+ TechClass.getRandomIndex() +"@a.com",
                TechClass.getRandomIndex(),
                "testUser"+TechClass.getRandomIndex());
        accessToken = postApi.apiReg(registerCard).getBody().path("accessToken");
    }
    @Test
    @DisplayName("Проверка успешной авторизации через кнопку в форме регистрации")
    public void formRegisterTest(){
        loginPage.openLoginPage();
        loginPage.doLogin(registerCard.getEmail(), registerCard.getPassword());
        assertEquals(registerCard.getEmail(),getApi.apiGetUser(accessToken));
    }
    @Test
    @DisplayName("Проверка успешной авторизации по кнопке «Войти в аккаунт» на главной странице")
    public void mainPageRegTest(){
        mainPage.openMainPage();
        mainPage.clickEnterAccountButton();
        loginPage.doLogin(registerCard.getEmail(), registerCard.getPassword());
        assertEquals(registerCard.getEmail(),getApi.apiGetUser(accessToken));
    }
    @Test
    @DisplayName("Проверка успешного входа через кнопку «Личный кабинет»")
    public void personalAccountRegTest(){
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        loginPage.doLogin(registerCard.getEmail(), registerCard.getPassword());
        assertEquals(registerCard.getEmail(),getApi.apiGetUser(accessToken));
    }

    //    вход через кнопку в форме восстановления пароля.
    @Test
    @DisplayName("Проверка успешного входа через кнопку в форме восстановления пароля")
    public void ForgotPageRegTest(){
        forgotPage.openForgotPage();
        forgotPage.clickLoginButton();
        loginPage.doLogin(registerCard.getEmail(), registerCard.getPassword());
        assertEquals(registerCard.getEmail(),getApi.apiGetUser(accessToken));
    }

    @After
    public void cleanUp(){
        driver.quit();
        deleteApi.deleteUser(accessToken);//удалить
    }
}
