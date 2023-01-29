package com.chefaa.bestbuy.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ProductsTest extends BaseProductsTest {
	
	// valid create product 
	
	public void testCreateValidProductSucceeds() {

		ObjectNode product = createValidProductJsonObject();

		ValidatableResponse response = createProduct(product).then();

		response.statusCode(201);
		response.body("id", notNullValue());
		response.body("createdAt", notNullValue());
		response.body("updatedAt", notNullValue());
		response.body("name", equalTo(product.get("name").asText()));

	}
	
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
