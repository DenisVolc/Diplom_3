package login;


import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import supertest.SuperTest;

import static org.junit.Assert.assertEquals;

public class LoginTests extends SuperTest {

    @Before
    public void setUp(){
        doBefore();
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
    @Test
    @DisplayName("Проверка успешного входа через кнопку в форме восстановления пароля")
    public void ForgotPageRegTest(){
        forgotPage.openForgotPage();
        forgotPage.clickLoginButton();
        loginPage.doLogin(registerCard.getEmail(), registerCard.getPassword());
        assertEquals(registerCard.getEmail(),getApi.apiGetUser(accessToken));
    }
}
