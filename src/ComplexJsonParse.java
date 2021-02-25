import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrice());
		int total1 = 0;
		// 1. Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// 2.Print Purchase Amount
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);

		// 3. Print Title of the first course
		String title = js.getString("courses[0].title");
		System.out.println(title);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < count; i++) {

			String coursetitles = js.get("courses[" + i + "].title");
			int amt = js.getInt("courses[" + i + "].price");

			System.out.println("The title is " + coursetitles + " and the amount is " + amt);
		}
		// 5. Print no of copies sold by RPA Course
		for (int i = 0; i < count; i++) {
			String coursetitle = js.get("courses[" + i + "].title");

			if (coursetitle.equals("RPA")) {
				int number = js.getInt("courses[" + i + "].copies");
				System.out.println("Total number of copies sold is " + number);
				break;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		for (int i = 0; i < count; i++) {
			int copies = js.getInt("courses[" + i + "].copies");
			int price = js.getInt("courses[" + i + "].price");

			total1 = total1 + copies * price;
		}

		if (purchaseAmt == total1) {
			System.out.println("The total is correct");
		} else {
			System.out.println("The total is not correct.");
		}
	}

}
