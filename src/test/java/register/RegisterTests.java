package register;

import http.json.LoginRequestCard;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import supertest.SuperTest;
import io.qameta.allure.Step;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;


public class RegisterTests extends SuperTest {

    @Before
    public void setUp() {
        doBefore();
        registerPage.openRegisterPage();
        loginCard = new LoginRequestCard(registerCard.getEmail(),registerCard.getPassword());
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registrationTest(){
        registerPage.doRegister(registerCard.getName(),registerCard.getEmail(),registerCard.getPassword());
        apiAuth(200,"user.email",loginCard.getEmail());
    }
    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void wrongPasswordRegTest(){
        registerPage.doRegister(registerCard.getName(),registerCard.getEmail(),registerCard.getPassword().substring(0,5));//первые 5 символом пароля
        assertTrue(registerPage.isWrongPasswordTitleVisible());
    }
//----------------------------------STEPS----------------------------------
    @Step("Авторизация")
    public void apiAuth(int statusCode, String bodyParm, String equalTo){
        Response response = postApi.apiAuth(loginCard);
        accessToken = response.getBody().path("accessToken");
        response.then().statusCode(statusCode)
                .and().assertThat().body(bodyParm,equalTo(equalTo));
    }

}
