package com.ujianmingguanke5;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostMovieRating {
    String endpoint = "https://api.themoviedb.org/3/movie/455476/rating";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMmVkMWQ0ZjlhMDhiY2EzNDk2NGIyNzMyMzI3NTliOCIsInN1YiI6IjVmZDYzMzY3N2UxMmYwMDAzYjcwYzcxOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jS27lGymcawShfB5CUEQusV8WtWe1yS9ZgheICmGwzY";

    @Test
    public void testPostAlbum() {
        JSONObject request = new JSONObject();
        request.put("value", "8.5");
        System.out.println(request.toJSONString());
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void validasiPostMovieRating() {
        given()
                .header("Authorization", "Bearer " + token)
                .get("https://api.themoviedb.org/3/movie/455476/account_states")
                .then()
                .statusCode(200)
                .body("rated.value", equalTo(8.5F));
    }
}
