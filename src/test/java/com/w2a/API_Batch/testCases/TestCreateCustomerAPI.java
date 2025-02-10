package com.w2a.API_Batch.testCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.API_Batch.setUp.APISetUp;
import com.w2a.API_Batch.testUtils.DataProviders;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreateCustomerAPI extends APISetUp {

	@Test(dataProviderClass = DataProviders.class,dataProvider = "dp")
	public void validateCreateCustomerWithValid(Hashtable<String, String> data) {

		testLevelLog.get().assignAuthor("Imran");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification req = setRequestSpecification().formParam("email", data.get("email"))
				.formParam("description", data.get("description")).formParam("balance", 10000).log().all();

		System.out.println("======================================================");
		
		Response response = req.post("customers");
		testLevelLog.get().info(response.asString());
		
		String response_email = response.path("email");
		String response_description = response.path("description");
		
		System.out.println("Email in response "+response_email);
		System.out.println("description in response "+response_description);
		
		System.out.println("Footer value is :- " + response.path("invoice_settings.footer"));
		
		
		response.prettyPrint();
		//Assert.assertEquals(response.getStatusCode(), 400);

	}

}
