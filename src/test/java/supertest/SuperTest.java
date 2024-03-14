package supertest;

import http.client.DeleteApi;
import http.client.GetApi;
import http.client.PostApi;
import http.json.LoginRequestCard;
import http.json.RegisterRequsetCard;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobjects.*;
import tech.TechClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class SuperTest {
    protected WebDriver driver;
    protected DeleteApi deleteApi = new DeleteApi();
    protected GetApi getApi = new GetApi();
    protected PostApi postApi = new PostApi();
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ForgotPage forgotPage;
    protected RegisterPage registerPage;
    protected RegisterRequsetCard registerCard;
    protected LoginRequestCard loginCard;
    protected String accessToken;
    protected TechClass browser = new TechClass();
    protected ProfilePage profilePage;
    private String browserName;



    public void doBefore() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Print \"chrome\" or \"yandex\".");
//        System.out.println("Enter prefer browser name: ");
//        browserName = reader.readLine();//todo разобраться как считать из консоль
//      //todo установить Yandex браузер
//        driver = browser.getWebDriver(browserName);
        driver = browser.getWebDriver("chrome");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        registerPage= new RegisterPage(driver);
        forgotPage = new ForgotPage(driver);

        registerCard = new RegisterRequsetCard(
                "test_user"+ TechClass.getRandomIndex() +"@a.com",
                TechClass.getRandomIndex(),
                "test_user"+TechClass.getRandomIndex()
        );

    }
    @After
    public void cleanUp(){
        driver.quit();
        deleteApi.deleteUser(accessToken);
    }
}
