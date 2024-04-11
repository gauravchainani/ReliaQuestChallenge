package com.example.rqchallenge.employees.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.employees.model.CreateEmployeeResponse;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController()
@Slf4j
public class EmployeeController implements IEmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> employeeList = employeeService.getAllEmployees();
			if(employeeList == null || employeeList.isEmpty())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
		} catch(Exception e) {
			log.error("Error getting all employees", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
		try {
			List<Employee> employeeList = employeeService.getEmployeesByNameSearch(searchString);
			if(employeeList == null || employeeList.isEmpty())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
		} catch(Exception e) {
			log.error("Error searching employee : " + searchString, e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(String id) {
		try {
			Employee employee = employeeService.getEmployeeById(id);
			if(employee == null)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} catch(Exception e) {
			log.error("Error getting employee by id ", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.getHighestSalary());
		} catch(Exception e) {
			log.error("Error getting highest salary of employees ", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
		try {
			List<String> employeeList = employeeService.getTopNHighestEarningEmployeeNames(10); // This can be extracted in static variable
			if(employeeList == null || employeeList.isEmpty())
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.status(HttpStatus.OK).body(employeeList);
		} catch(Exception e) {
			log.error("Error getting top ten highest earning employees ", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<CreateEmployeeResponse> createEmployee(Map<String, Object> employeeInput) {
		try {
			CreateEmployeeResponse employee = employeeService.createEmployee(employeeInput);
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} catch(Exception e) {
			log.error("Error creating employee ", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(String id) {
		try {
			employeeService.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch(Exception e) {
			log.error("Error deleting employee with id : " + id, e);
			return ResponseEntity.internalServerError().build();
		}
	}

}
