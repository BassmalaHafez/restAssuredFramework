package com.chefaa.bestbuy.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.*;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;


@Test(priority = 1)
public class GetProductsTest extends BaseProductsTest {

	
	public void testResoponseIsOk() {
		// Validate response code
		Response resp = RestAssured.get("http://localhost:3030/products");

		int code = resp.getStatusCode();

		Assert.assertEquals(code, 200);

	}

	
	
	public void testProductsCountIsValid() {

		// Validate the total count of products
		ValidatableResponse resp = RestAssured.get("http://localhost:3030/products").then();

		resp.body("total", equalTo(51976));

	}

	
	public void testProductsCountWithLimitIsValid() {

		// validate the total count of products after applying limitation

		Response resp = RestAssured.get("http://localhost:3030/products?$limit=10");

		List<Object> data = resp.body().path("data");

		Assert.assertEquals(data.size(), 10);

	}
	

}
