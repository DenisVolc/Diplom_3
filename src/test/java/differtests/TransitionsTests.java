package differtests;

import http.client.DeleteApi;
import http.client.GetApi;
import http.client.PostApi;
import http.json.LoginRequestCard;
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

public class TransitionsTests {
    WebDriver driver;
    DeleteApi deleteApi = new DeleteApi();
    GetApi getApi = new GetApi();
    PostApi postApi = new PostApi();
    LoginPage loginPage;
    MainPage mainPage;
    ProfilePage profilePage;
    RegisterRequsetCard registerCard;
    LoginRequestCard loginCard;
    private String accessToken;
    TechClass browser = new TechClass();

    @Before
    public void setUp(){
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);

        registerCard = new RegisterRequsetCard(
                "testUser"+ TechClass.getRandomIndex() +"@a.com",
                TechClass.getRandomIndex(),
                "testUser"+TechClass.getRandomIndex());
        loginCard = new LoginRequestCard(
                registerCard.getEmail(),
                registerCard.getPassword()
        );
        accessToken = postApi.apiReg(registerCard).getBody().path("accessToken");
        postApi.apiAuth(loginCard);
    }


    @Test
    @DisplayName("Проверка переход по клику на «Личный кабинет»")
    public void personalAccTransitionTest(){
        mainPage.openMainPage();
        mainPage.clickPersonalAccountButton();
        assertTrue(profilePage.isKeyTitleDisplayed());
    }
    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на кнопку «Конструктор»")
    public void constructButtonTransitionTest(){
        profilePage.openProfilePage();
        profilePage.clickConstructorButton();
        assertTrue(mainPage.isMakeBurgerTitleDisplayed());
    }
    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на кнопку «Конструктор»")
    public void logoTransitionTest(){
        profilePage.openProfilePage();
        profilePage.clickLogo();
        assertTrue(mainPage.isMakeBurgerTitleDisplayed());
    }

    @After
    public void cleanUp(){
        driver.quit();
        deleteApi.deleteUser(accessToken);//удалить
    }
}
