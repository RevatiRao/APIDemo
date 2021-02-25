package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
		.body(Payload.addBook(isbn,aisle))
		.when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js = reUseableMethods.rawToJson(response);
		String id = js.getString("ID");
		System.out.println(id);
		
		//Delete Book
	
		
		}
	@DataProvider(name="BooksData")
	public Object[][] getData() 
	{
		return new Object[][] {{"ajdirf","4565"},{"ergjrbf","4566"},{"frowhbrod","4567"}};
		
	}
}
