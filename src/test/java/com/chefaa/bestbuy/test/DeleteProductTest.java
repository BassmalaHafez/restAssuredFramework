package com.chefaa.bestbuy.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@Test
public class DeleteProductTest extends BaseProductsTest {
	
	public void testDeleteProduct()
	{
		ObjectNode product = createValidProductJsonObject();

		Response response = createProduct(product);

		Integer productId = response.body().path("id");
		
		ValidatableResponse deleteResponse = RestAssured.delete("http://localhost:3030/products/{id}", productId).then();

		deleteResponse.statusCode(200);
	
		
	}
	// Delete product not found 
	public void testInValidDeleteProduct() 
	{
		Response resp = RestAssured.delete("http://localhost:3030/products/1");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 404);
	}

}
