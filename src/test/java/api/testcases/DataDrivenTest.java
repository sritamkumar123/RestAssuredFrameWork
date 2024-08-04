package api.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endPOINTS.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class DataDrivenTest {
	
        User userPayload;

	    @Test(priority=1,dataProvider = "excelData",dataProviderClass= DataProviderClass.class)
	    public void testCreateUser(String userid, String username, String fname ,String lname , String email , String pwd, String phone) {

	    	userPayload =new User();
	    	userPayload.setId(Integer.parseInt(userid));
	    	userPayload.setUsername(username);
	    	userPayload.setFirstname(fname);
	    	userPayload.setLastname(lname);
	    	userPayload.setEmail(email);
	    	userPayload.setPassword(pwd);
	    	userPayload.setPhone(phone);
	    	
	    	Response res = UserEndPoints.createuser(userPayload);
	    	
	    	System.out.println("------------------Created new users---------------------------------");
	    	System.out.println("\t");
	    	res.then().log().all();
	    	
	    	Assert.assertEquals(res.statusCode(), 200,"Sttaus code should be 200");
	    	
	    }
	    
	    @Test(priority=2,dataProvider = "excelData",dataProviderClass= DataProviderClass.class)
	    public void testGetusers (String userid, String username, String fname ,String lname , String email , String pwd, String phone) {
	    	Response res1 = UserEndPoints.get_user(username);
	    	System.out.println("-------------Get the created users-------------");
	    	System.out.println("\t");
	    	res1.then().log().all();
	    	
	    	Assert.assertEquals(res1.statusCode(), 200,"Status code should be 200");
	    	
	    }
	    
	    @Test(priority=3, dataProvider ="excelData", dataProviderClass= DataProviderClass.class)
	    
	    public void testUpdateUsers(String userid, String username, String fname ,String lname , String email , String pwd, String phone) {
	    	
	    	userPayload.setLastname(lname);
	    	 Response res2 = UserEndPoints.update_user(username, userPayload);
	    	 
	    	 System.out.println("-----------------------------Updated users ------------------------------");
	    	 System.out.println("\t");
	    	 res2.then().log().all();
	    	 
	    	 Assert.assertEquals(res2.statusCode(), 200,"Status code should be 200");
	    }
	    
	    @Test(priority=4, dataProvider="excelData",dataProviderClass=DataProviderClass.class)
	    public void testDeleteuser(String userid, String username, String fname ,String lname , String email , String pwd, String phone) {
	    	
	    	Response res = UserEndPoints.delete_user(username);
	    	
	    	System.out.println("Deleted created users");
	    	System.out.println("\t");
	    	
	    	res.then().log().all();
	    	
	    	Assert.assertEquals(res.statusCode(), 200, "status code should be 200");
	    
	    	
	    }
	    
	    
}
