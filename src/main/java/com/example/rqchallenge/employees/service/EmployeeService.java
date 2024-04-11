package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.datasource.EmployeeDataSource;
import com.example.rqchallenge.employees.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
	
	@Autowired
	private EmployeeDataSource employeeDataSource;

	ObjectMapper objectMapper = new ObjectMapper();
	
	private String JSON_NODE_DATA = "data";

	public List<Employee> getAllEmployees() throws Exception{
		String employeesResponse = employeeDataSource.getAllEmployees();
		log.trace("getAllEmployees repsonse : " + employeesResponse);
		try{
			JSONObject responseJson = new JSONObject(employeesResponse);
			if(responseJson.has(JSON_NODE_DATA))
				return Arrays.asList(objectMapper.readValue(responseJson.get(JSON_NODE_DATA).toString(), Employee[].class));
			log.debug("No Data field in response json");
			return null;
		} catch (Exception e) {
			log.error("Error while parsing all employees data", e);
			throw e;
		}
	}
	
	public List<Employee> getEmployeesByNameSearch(String name) throws Exception{
		List<Employee> allEmployees = getAllEmployees();
		if(allEmployees != null && !allEmployees.isEmpty()) {
			return allEmployees
					.stream()
					.filter(employee -> employee.getName().toLowerCase().contains(name.toLowerCase()))
					.collect(Collectors.toList());
		}
		return null;
	}
	
	public Employee getEmployeeById(String id) throws Exception{
		String employeeResponse = employeeDataSource.getEmployeeById(id);
		log.trace("getEmployeeById repsonse : " + employeeResponse);
		try{
			JSONObject responseJson = new JSONObject(employeeResponse);
			if(responseJson.has(JSON_NODE_DATA))
				return objectMapper.readValue(responseJson.get(JSON_NODE_DATA).toString(), Employee.class);
			log.debug("No Data field in response json");
			return null;
		} catch (Exception e){
			log.error("Error while parsing employee data for id : " + id, e);
			throw e;
		}
	}

	public Integer getHighestSalary() throws Exception{
		List<Employee> allEmployees = getAllEmployees();
		Integer highestSalary = 0;
		if(allEmployees != null && !allEmployees.isEmpty()){
			for(Employee employee : allEmployees) {
				if(employee.getSalary() > highestSalary){
					highestSalary = employee.getSalary();
				}
			}
		}
		return highestSalary;
	}

	public List<String> getTopNHighestEarningEmployeeNames(int n) throws Exception{
		List<Employee> allEmployees = getAllEmployees();
		if(allEmployees != null && !allEmployees.isEmpty()) {
			Collections.sort(allEmployees, new SalaryComparator());
			return allEmployees.
					stream()
					.limit(n)
					.map(employee -> employee.getName())
					.collect(Collectors.toList());
		}
		return null;
	}

	public CreateEmployeeResponse createEmployee(Map<String, Object> employeeInput) throws Exception{
		String createEmployeeResponse = employeeDataSource.createEmployee(employeeInput);
		log.trace("createEmployee repsonse : " + createEmployeeResponse);
		try{
			JSONObject responseJson = new JSONObject(createEmployeeResponse);
			if(responseJson.has(JSON_NODE_DATA))
				return objectMapper.readValue(responseJson.get(JSON_NODE_DATA).toString(), CreateEmployeeResponse.class);
			log.debug("No Data field in response json");
			return null;
		} catch (Exception e){
			log.error("Error while parsing employee data : " + employeeInput, e);
			throw e;
		}
	}

	public void deleteEmployee(String id) throws Exception{
		employeeDataSource.deleteEmployee(id);
	}
}
