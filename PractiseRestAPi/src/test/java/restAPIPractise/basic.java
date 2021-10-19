package restAPIPractise;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class basic {
    public static  void main(String args[]){

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.3409494,\n" +
                        "    \"lng\": 33.4345962\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"seoull korea korea\",\n" +
                        "  \"phone_number\": \"(+91) 993 893 3937\",\n" +
                        "  \"address\": \"702, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoes park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}").when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);
    }

}
