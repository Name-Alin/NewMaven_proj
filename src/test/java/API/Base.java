package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Base {

    @Test
    public void ApiTest()
    {
        //given, when, then
        //everything after assert is validation
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String respons =  given().log().all().queryParam("key","qaclick123").header("content-type","application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
        System.out.println(respons);

        JsonPath js = new JsonPath(respons); //parsing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        //Update place with new address:

        String newAddress = "Summer Walk, Africa";
        given().log().all().queryParam("key","qaclick123").header("content-type","application/json")
                .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
                .when().put("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get Place
        String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/add/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath js1 = new JsonPath(getPlaceResponse);
        String getAddress = js1.getString("address");
        System.out.println(getAddress);
        Assert.assertEquals(getAddress, newAddress);

    }

    @Test
    public void ComplexJsonParser() {
        JsonPath js = new JsonPath(payload.CoursePrice());
        //get size
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //get amount from json
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //get title from json
        String titleFirstCourse = js.get("courses[0].title");
        System.out.println(titleFirstCourse);

        // get all the titles from Json
        for (int i=0; i<count; i++){
            String courseTitles = js.get("courses["+i+"].title");
            System.out.println(js.get("courses["+i+"].price").toString());
            System.out.println(courseTitles);

        }

        //get copies from a specific title
        for (int i=0; i<count; i++) {
            String courseTitles = js.get("courses[" + i + "].title");
            if (courseTitles.equalsIgnoreCase("RPA"))
            {
                int copiesCount = js.get("courses[" + i + "].copies");
                System.out.println("Copies count:" + copiesCount);
            }
        }

        //Verify if sum of all course prices maches with Purchase Amount
        int sum = 0;
        for (int i=0; i<count; i++) {

            int coursePrice = js.get("courses[" + i + "].price");
            int copiesCount = js.get("courses[" + i + "].copies");
            int amount = coursePrice * copiesCount;
            sum = sum + amount;


        }
        System.out.println("Amount: " + sum);
        Assert.assertEquals(sum,totalAmount);

    }
}
