package api.endPOINTS;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	public  Response createuser(User payload) {
	    Response response= RestAssured.given()
	    .contentType(ContentType.JSON)
	    .accept(ContentType.JSON)
	    .body(payload)
	    .when().post(Routes.post_url);
	    
	    return response;
	    
	}
	
	public  Response get_user(String pathParam) {
	    Response response= RestAssured.given()
	    .pathParam("username", pathParam)
	    .accept(ContentType.JSON)
	    .when().get(Routes.get_url);
	    
	    return response;
	    
	}
	
	public  Response update_user(String pathParam, User payload) {
	    Response response= RestAssured.given()
	    .pathParam("username", pathParam)
	    .contentType(ContentType.JSON)
	    .accept(ContentType.JSON).body(payload)
	    .when().put(Routes.update_url);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																															
	    
	    return response;
	    
	}
	
	public  Response delete_user(String pathParam) {
	    Response response= RestAssured.given()
	    .pathParam("username", pathParam)
	    .accept(ContentType.JSON)
	    .when().delete(Routes.delete_url);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																															
	    
	    return response;
	    
	}
}
