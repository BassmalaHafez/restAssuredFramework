package com.chefaa.bestbuy.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

@Test
public class CreateProductTest extends BaseProductsTest {

	
	
//	  "name": "Duracell - C Batteries (4-Pack)",
//    "type": "HardGood",
//    "price": 8.99,
//    "upc": "041333440019",
//    "shipping": 0,
//    "description": "Compatible with select electronic devices; C size; DURALOCK Power Preserve technology; 4-pack",
//    "manufacturer": "Duracell",
//    "model": "MN1400R4Z",
//    "url": "http://www.bestbuy.com/site/duracell-c-batteries-4-pack/185230.p?id=1051384046486&skuId=185230&cmp=RMXCC",
//    "image": "http://img.bbystatic.com/BestBuy_US/images/products/1852/185230_sa.jpg",
//
//	{
//		  "id": 9999679,
//		  "name": "string",
//		  "type": "string",
//		  "price": 0,
//		  "shipping": 0,
//		  "upc": "string",
//		  "description": "string",
//		  "manufacturer": "string",
//		  "model": "string",
//		  "url": "string",
//		  "image": "string",
//		  "updatedAt": "2022-06-11T16:17:54.614Z",
//		  "createdAt": "2022-06-11T16:17:54.614Z"
//	}

	public void testCreateValidProductSucceeds() {

		ObjectNode product = createValidProductJsonObject();

		ValidatableResponse response = createProduct(product).then();

		response.statusCode(201);
		response.body("id", notNullValue());
		response.body("createdAt", notNullValue());
		response.body("updatedAt", notNullValue());
		response.body("name", equalTo(product.get("name").asText()));

	}



	public void testCreateInvalidProductFails() {

		ObjectNode product = createValidProductJsonObject();

		product.putNull("name");

		ValidatableResponse response = given().body(product).contentType(ContentType.JSON).accept(ContentType.JSON)
			.post("http://localhost:3030/products").then();

		response.statusCode(400);
	}
	
}

