package com.MPocket.OnlineAssesment;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtils.BaseAPIClass;
import GenericUtils.Endpointss;
import Deserialize.ResponseTest;
import Deserialize.GetResponseTest;
import POJO.Data;
import POJO.Employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


	public class CreateEmployeeTest extends BaseAPIClass {


		@Test
		public void createEmployeeTest() {
			Data data=new Data("Amrutha", "1290", "29", 20);

			Employee employee = new Employee("success", data);

			ResponseTest response = RestAssured.given()
					.contentType(ContentType.JSON)
					.body(employee)
					.when()
					.post(Endpointss.addEmployee);
			response.then()
			.log().all();

			int empId=response.jsonPath().get("data.id");
			ResponseTest employeeRes = response.getBody().as(ResponseTest.class);
			Assert.assertEquals(employeeRes.getMessage(),"Successfully! Record has been added.");
			Assert.assertEquals(employeeRes.getStatus(),employee.getStatus());


			Response getResponse = RestAssured.given()
					.pathParam("id", empId)
					.get(Endpointss.getEmployee);
			getResponse.then().log().all();

			GetResponseTest getEmployeeData = getResponse.getBody().as(GetResponseTest.class);
			Assert.assertEquals(getEmployeeData.getStatus(),employee.getStatus());
			Assert.assertEquals(getEmployeeData.getMessage(),"Successfully! Record has been fetched.");


			RestAssured.given()
			.pathParam("id", empId)
			.delete(Endpointss.deleteEmployee)
			.then()
			.log().all();

		}
	}

}
