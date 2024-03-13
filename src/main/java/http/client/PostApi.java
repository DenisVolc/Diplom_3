package http.client;

import http.EndPoints;
import http.client.BaseHttpClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostApi extends BaseHttpClient {
    public Response doPost(String path,Object body){
        return doPostRequest(path,body).thenReturn();
    }
    public Response doPostWithAuth(String path,Object body,String token){
        return doPostRequestWithAuth(path,body,token).thenReturn();
    }
    @Step("Авторизация через API")
    public Response apiAuth(Object loginCard){
        Response response = doPost(EndPoints.LOGIN,loginCard);
        return response;
    }
    @Step("Регистрация через API")
    public Response apiReg(Object registerCard){
        Response response = doPost(EndPoints.REGISTER,registerCard);
        return response;
    }

}
