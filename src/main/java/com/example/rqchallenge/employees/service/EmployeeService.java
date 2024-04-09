package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.datasource.EmployeeDataSource;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.model.EmployeeResponse;
import com.example.rqchallenge.employees.model.EmployeesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {
	
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
		EmployeeResponse employeeResponse = employeeDataSource.getEmployeeById(id);
		
		return employeeResponse.getData();
	}

}
