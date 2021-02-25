import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; //manually entered
import static org.hamcrest.CoreMatchers.*;//manually entered

import org.testng.Assert;

import files.Payload;
import files.reUseableMethods;

public class Basics {

	public static void main(String[] args) {

		// Validate if add place API works as expected.

		// RESTAssured works on 3 principles/methods - given, when, then.
		// given - all input details
		// when - submit the API(get, post, put, delete) - resource, http method
		// then - validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = 
				given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.AddPlace()) //using addPlace method.
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Connection", "Keep-Alive")
				.extract().response().asString();

		System.out.println(response);
		
		//JsonPath - Takes string as input and converts that into JSON to parse JSON.
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		System.out.println(placeID);
		
		//Update place
		// Add Place --> Update Place with new address --> Get Place to validate if new
				// Address is present in response
		
		String newAddress="70 Summer Walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\": \""+ placeID +"\",\r\n" + 
				"\"address\":\"" + newAddress +"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = reUseableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		
		//Using testng assertion	
		Assert.assertEquals(actualAddress, newAddress);
		
	}

}
