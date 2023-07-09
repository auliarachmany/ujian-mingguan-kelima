package com.ujianmingguanke5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestGetMoviePopular {
    String endpoint = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMmVkMWQ0ZjlhMDhiY2EzNDk2NGIyNzMyMzI3NTliOCIsInN1YiI6IjVmZDYzMzY3N2UxMmYwMDAzYjcwYzcxOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jS27lGymcawShfB5CUEQusV8WtWe1yS9ZgheICmGwzY";

    @Test
    public void testStatusCode() {

        Response response =
                given()
                        .headers(
                                "Authorization",
                                "Bearer " + token,
                                "Content-Type",
                                ContentType.JSON,
                                "Accept",
                                ContentType.JSON)
                        .when()
                        .get(endpoint)
                        .then()
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();

        int actual = response.getStatusCode();
        Assert.assertEquals(actual, 200);


    }

    @Test
    public void testValidaseGetMoviePopular() {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("results.original_title[0]", equalTo("Knights of the Zodiac"));
    }
}
