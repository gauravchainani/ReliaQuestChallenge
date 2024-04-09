package com.example.rqchallenge.employees.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.rqchallenge.employees.model.EmployeeResponse;
import com.example.rqchallenge.employees.model.EmployeesResponse;

@Service
public class EmployeeDataSource {

	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDataSource.class);
	
	@Value("${employeeurl.baseurl}")
	private String baseurl;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public EmployeesResponse getAllEmployees() throws Exception {
		String url = baseurl + "employees";
		try {
			return restTemplate().getForObject(url, EmployeesResponse.class);
		} catch(Exception e) {
			LOGGER.error("Encountered Exception with REST API : " + url, e.getMessage());
			//Returning mocked response
			// We can have exception handling here to delegate the exception to show it to user
			//return new ObjectMapper().readValue("{\"status\": \"success\",\"data\": [{\"id\": \"1\",\"employee_name\": \"Tiger Nixon\",\"employee_salary\": \"320800\",\"employee_age\": \"61\",\"profile_image\": \"\"},{\"id\": \"2\",\"employee_name\": \"Tiger Nixon2\",\"employee_salary\": \"600000\",\"employee_age\": \"58\",\"profile_image\": \"\"},{\"id\": \"3\",\"employee_name\": \"Tiger Nixon3\",\"employee_salary\": \"350800\",\"employee_age\": \"50\",\"profile_image\": \"\"},{\"id\": \"4\",\"employee_name\": \"Tiger Nixon4\",\"employee_salary\": \"250000\",\"employee_age\": \"40\",\"profile_image\": \"\"},{\"id\": \"5\",\"employee_name\": \"Tiger Nixon5\",\"employee_salary\": \"2000000\",\"employee_age\": \"30\",\"profile_image\": \"\"}]}", EmployeesResponse.class);
			return null;
		}
	}
	
	public EmployeeResponse getAllEmployeeById(String id) throws Exception {
		String url = baseurl + "employee/" + id;
		try {
			return restTemplate().getForObject(url, EmployeeResponse.class);
		} catch(Exception e) {
			LOGGER.error("Encountered Exception with REST API : " + url, e.getMessage());
			//Returning mocked response
			// We can have exception handling here to delegate the exception to show it to user
			//return new ObjectMapper().readValue("{\"status\": \"success\",\"data\": [{\"id\": \"1\",\"employee_name\": \"Tiger Nixon\",\"employee_salary\": \"320800\",\"employee_age\": \"61\",\"profile_image\": \"\"},{\"id\": \"2\",\"employee_name\": \"Tiger Nixon2\",\"employee_salary\": \"600000\",\"employee_age\": \"58\",\"profile_image\": \"\"},{\"id\": \"3\",\"employee_name\": \"Tiger Nixon3\",\"employee_salary\": \"350800\",\"employee_age\": \"50\",\"profile_image\": \"\"},{\"id\": \"4\",\"employee_name\": \"Tiger Nixon4\",\"employee_salary\": \"250000\",\"employee_age\": \"40\",\"profile_image\": \"\"},{\"id\": \"5\",\"employee_name\": \"Tiger Nixon5\",\"employee_salary\": \"2000000\",\"employee_age\": \"30\",\"profile_image\": \"\"}]}", EmployeesResponse.class);
			return null;
		}
	}
	
	
	
}
