package com.example.rqchallenge.employees.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rqchallenge.employees.datasource.EmployeeDataSource;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.EmployeeResponse;
import com.example.rqchallenge.employees.model.EmployeesResponse;

@Service
public class EmployeeService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeDataSource employeeDataSource;

	public List<Employee> getAllEmployees() throws Exception {
		EmployeesResponse employeesResponse = employeeDataSource.getAllEmployees();
		
		return employeesResponse.getData();
		
	}
	
	public List<Employee> getEmployeesByNameSearch(String name) throws Exception{
		List<Employee> allEmployees = getAllEmployees();
		
		List<Employee> retList = new ArrayList<>();
		
		if(allEmployees != null && !allEmployees.isEmpty()) {
			for(Employee employee : allEmployees) {
				if(employee.getName().contains(name)) {
					retList.add(employee);
				}
			}
		}
		
		return retList;
	}
	
	public Employee getEmployeeById(String id) throws Exception{
		EmployeeResponse employeeResponse = employeeDataSource.getAllEmployeeById(id);
		
		return employeeResponse.getData();
	}

}
