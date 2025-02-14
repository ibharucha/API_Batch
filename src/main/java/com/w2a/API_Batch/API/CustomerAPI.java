package com.w2a.API_Batch.API;

import java.util.Hashtable;

import com.w2a.API_Batch.setUp.APISetUp;
import com.w2a.API_Batch.testUtils.TestUtil;

import io.restassured.response.Response;

public class CustomerAPI extends APISetUp {

	public static Response sendPostRequestWithValidDataWithArguments(Hashtable<String, String> data) {

		Response response = TestUtil.setFormParam(data.get("arguments"), setRequestSpecification())
				.post(data.get("endpoint"));
		return response;

	}

}
