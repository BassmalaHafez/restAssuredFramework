package com.chefaa.bestbuy.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@Test
public class GetProducrByIdTest extends BaseProductsTest{

	public void getProductByIdSucceeds() {

		ObjectNode product = createValidProductJsonObject();

		Response response = createProduct(product);

		Integer productId = response.body().path("id");
		ObjectNode productid = mapper.createObjectNode();


		ValidatableResponse getResponse = given().body(productid).contentType(ContentType.JSON).accept(ContentType.JSON)
				.get("http://localhost:3030/products/{id}", productId).then();
		
		getResponse.statusCode(200);

}
	
	
}