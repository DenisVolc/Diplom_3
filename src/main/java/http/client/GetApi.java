package http.client;

import http.EndPoints;

public class GetApi extends BaseHttpClient{
    public String apiGetUser(String accessToken){
        return doGetRequest(EndPoints.USER, accessToken).getBody().path("user.email");
    }
}
