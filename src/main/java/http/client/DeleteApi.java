package http.client;

import http.EndPoints;

public class DeleteApi extends BaseHttpClient{
    public void deleteUser(String token){
        if(token!=null) {
            doDeleteRequest(EndPoints.USER, token)
                    .then().statusCode(202);
        }
    }
}
