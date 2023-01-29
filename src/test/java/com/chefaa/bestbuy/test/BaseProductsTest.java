package com.chefaa.bestbuy.test;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseProductsTest {

	protected ObjectMapper mapper = new ObjectMapper();

	protected ObjectNode createValidProductJsonObject() {

		ObjectNode product = mapper.createObjectNode();

		product.put("name", "Duracell - C Batteries (12-Pack)");
		product.put("type", "HardGood");
		product.put("price", 15.5);
		product.put("shipping", 2);
		product.put("upc", "041333446412");
		product.put("description", "Compatible with select electronic devices; C size; DURALOCK Power Preserve technology; 4-pack");
		product.put("manufacturer", "Duracell");
		product.put("model", "MN1400R4F");
		product.put("url", "http://www.bestbuy.com/site/duracell-c-batteries-4-pack/185230.p?id=1051384046486&skuId=185230&cmp=RMXCC");
		product.put("image", "http://img.bbystatic.com/BestBuy_US/images/products/1852/185230_sa.jpg");

		return product;
	}

	protected Response createProduct(ObjectNode product) {

		return given().body(product).contentType(ContentType.JSON).accept(ContentType.JSON)
				.post("http://localhost:3030/products");
	}
}
