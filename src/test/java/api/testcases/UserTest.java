package api.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPOINTS.UserEndPoints;
import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setPayload() {
		RestAssured.config = RestAssured.config()
			    .sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
		
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	@Test(priority=1)
	public void testCreateUser() {
		
		Response response = UserEndPoints.createuser(userPayload);
		
		System.out.println("New User created");
		// log response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.statusCode(), 200,"Staus code should be 200");
		
		
	}
	@Test(priority=2)
	public void testGetUser() {
		
		Response response = UserEndPoints.get_user(userPayload.getUsername());
		
		System.out.println("Fetch the new created user");
		// log response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.statusCode(), 200,"Staus code should be 200");
		
	}
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setFirstname(faker.name().firstName());
		Response response = UserEndPoints.update_user(userPayload.getUsername(),userPayload);
		
		// log response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.statusCode(), 200,"Staus code should be 200");
		
		System.out.println("Updated the new created user");
		
		Response resGet = UserEndPoints.get_user(userPayload.getUsername());
		resGet.then().log().all();
		
	}
	@Test(priority=4)
	public void testDeeleteUser() {
		
		Response response = UserEndPoints.delete_user(userPayload.getUsername());
		
		System.out.println("Delete the new created user");
		
		// log response 
		response.then().log().all();
		
		//validation
		Assert.assertEquals(response.statusCode(), 200,"Staus code should be 200");
		
	}
	

}
