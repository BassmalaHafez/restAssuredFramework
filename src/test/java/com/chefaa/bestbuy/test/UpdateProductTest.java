package com.chefaa.bestbuy.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@Test
class UpdateProductTest extends BaseProductsTest {

	public void updateProductSucceeds() {

		ObjectNode product = createValidProductJsonObject();

		Response response = createProduct(product);

		Integer productId = response.body().path("id");

		String newName = "Duracell - Z Batteries (8-Pack)";

		ObjectNode productUpdate = mapper.createObjectNode();

		productUpdate.put("name", newName);

		ValidatableResponse updateResponse = given().body(productUpdate).contentType(ContentType.JSON).accept(ContentType.JSON)
				.patch("http://localhost:3030/products/{id}", productId).then();

		updateResponse.statusCode(200);
		updateResponse.body("id", Matchers.equalTo(productId));
		updateResponse.body("name", Matchers.equalTo(newName));

	}
	
	public void updateInValidProduct() {
		
		Response resp = RestAssured.patch("http://localhost:3030/products/1");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 404);
	}
	
	
	
	

}
