package com.example.rqchallenge.employees.impl;

import com.example.rqchallenge.employees.IEmployeeController;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/employee")
@RestController()
@Slf4j
public class EmployeeController implements IEmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public ResponseEntity<List<Employee>> getAllEmployees() throws IOException {
		ResponseEntity<List<Employee>> responseEntity = null;
		try {
			List<Employee> employeeList = employeeService.getAllEmployees();
			responseEntity = new ResponseEntity<>(employeeList, HttpStatus.OK);
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}

	@Override
	@GetMapping("/search/{searchString}")
	public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
		ResponseEntity<List<Employee>> responseEntity = null;
		try {
			List<Employee> employeeList = employeeService.getEmployeesByNameSearch(searchString);
			responseEntity = new ResponseEntity<>(employeeList, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(String id) {
		ResponseEntity<Employee> responseEntity = null;
		try {
			Employee employee = employeeService.getEmployeeById(id);
			responseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
		}catch(Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
