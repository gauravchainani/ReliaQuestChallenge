package com.example.rqchallenge.employees.datasource;

import com.example.rqchallenge.employees.model.EmployeeResponse;
import com.example.rqchallenge.employees.model.EmployeesResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeDataSource {
	
	@Value("${employeeurl.baseurl}")
	private String baseurl;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Retryable(retryFor = RestClientException.class)
	public <T> T getCall(Class<T> classType, String url){
		return restTemplate().getForObject(url, classType);
	}

	public EmployeesResponse getAllEmployees() throws RestClientException {
		String url = baseurl + "employees";
		return getCall(EmployeesResponse.class, url);
	}
	
	public EmployeeResponse getEmployeeById(String id) throws RestClientException {
		String url = baseurl + "employee/" + id;
		return getCall(EmployeeResponse.class, url);
	}
}
