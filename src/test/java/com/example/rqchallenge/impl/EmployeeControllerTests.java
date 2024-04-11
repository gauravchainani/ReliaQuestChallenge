package com.example.rqchallenge.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.example.rqchallenge.employees.impl.EmployeeController;
import com.example.rqchallenge.employees.model.CreateEmployeeResponse;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;

@SpringBootTest
public class EmployeeControllerTests {
	
	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeService employeeService;
	
	@BeforeTestMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	void testGetAllEmployees() throws Exception{
		
		Employee employee1 = new Employee("1", "Gaurav", 1, 1, "");
		Employee employee2 = new Employee("2", "Kishore", 1, 1, "");
		Employee employee3 = new Employee("3", "Kanta", 1, 1, "");
		List<Employee> actualValue = new ArrayList<>();
		actualValue.add(employee1);
		actualValue.add(employee2);
		actualValue.add(employee3);
		
		Mockito.when(
				employeeService.getAllEmployees()
			).thenReturn(actualValue);
		
		ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();
		
		Assertions.assertEquals(3, responseEntity.getBody().size());
		Assertions.assertEquals(1, responseEntity.getBody().get(1).getAge());
		Assertions.assertEquals(1, responseEntity.getBody().get(1).getSalary());
	}
	
	@Test
	void testGetAllEmployeesNull() throws Exception{
		
		Mockito.when(
				employeeService.getAllEmployees()
			).thenReturn(new ArrayList<>());
		
		ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();
		
		Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
	
	@Test
	void testGetAllEmployeesException() throws Exception{
		
		Mockito.when(
				employeeService.getAllEmployees()
			).thenThrow(new Exception());
		
		ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}
	
	@Test
	void testGetEmployeesByNameSearch() throws Exception{
		
		Employee employee1 = new Employee("1", "Gaurav1", 1, 1, "");
		Employee employee2 = new Employee("2", "Gaurav2", 1, 1, "");
		Employee employee3 = new Employee("3", "Gaurav3", 1, 1, "");
		List<Employee> actualValue = new ArrayList<>();
		actualValue.add(employee1);
		actualValue.add(employee2);
		actualValue.add(employee3);
		
		Mockito.when(
				employeeService.getEmployeesByNameSearch("Gaurav")
			).thenReturn(actualValue);
		
		ResponseEntity<List<Employee>> responseEntity = employeeController.getEmployeesByNameSearch("Gaurav");
		
		Assertions.assertEquals(3, responseEntity.getBody().size());
		Assertions.assertEquals(1, responseEntity.getBody().get(1).getAge());
		Assertions.assertEquals(1, responseEntity.getBody().get(1).getSalary());
	}
	
	@Test
	void testGetEmployeesByNameSearchNull() throws Exception{
		
		Mockito.when(
				employeeService.getEmployeesByNameSearch("Gaurav")
			).thenReturn(new ArrayList<>());
		
		ResponseEntity<List<Employee>> responseEntity = employeeController.getEmployeesByNameSearch("Gaurav");
		
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
	@Test
	void testGetEmployeesByNameSearchException() throws Exception{
		
		Mockito.when(
				employeeService.getEmployeesByNameSearch("Gaurav")
			).thenThrow(new Exception());
		
		ResponseEntity<List<Employee>> responseEntity = employeeController.getEmployeesByNameSearch("Gaurav");
		
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	void testGetEmployeeById() throws Exception{
		
		Employee employee1 = new Employee("1", "Gaurav", 1, 1, "");
		
		Mockito.when(
				employeeService.getEmployeeById("1")
			).thenReturn(employee1);
		
		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById("1");
		
		Assertions.assertEquals("Gaurav", responseEntity.getBody().getName());
	}
	
	@Test
	void testGetEmployeeByIdNull() throws Exception{
		
		Mockito.when(
				employeeService.getEmployeeById("1")
			).thenReturn(null);
		
		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById("1");
		
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
	@Test
	void testGetEmployeeByIdException() throws Exception{
		
		Mockito.when(
				employeeService.getEmployeeById("1")
			).thenThrow(new Exception());
		
		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById("1");
		
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	void testGetHighestSalaryOfEmployees() throws Exception{
		
		Mockito.when(
				employeeService.getHighestSalary()
			).thenReturn(100000);
		
		ResponseEntity<Integer> responseEntity = employeeController.getHighestSalaryOfEmployees();
		
		Assertions.assertEquals(100000, responseEntity.getBody());
	}
	
	@Test
	void testGetHighestSalaryOfEmployeesException() throws Exception{
		
		Mockito.when(
				employeeService.getHighestSalary()
			).thenThrow(new Exception());
		
		ResponseEntity<Integer> responseEntity = employeeController.getHighestSalaryOfEmployees();
		
		Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	void testGetTopTenHighestEarningEmployeeNames() throws Exception{
		
		List<String> actualValue = Arrays.asList("Gaurav1", "Gaurav2", "Gaurav3");
		
		Mockito.when(
				employeeService.getTopNHighestEarningEmployeeNames(10)
			).thenReturn(actualValue);
		
		ResponseEntity<List<String>> responseEntity = employeeController.getTopTenHighestEarningEmployeeNames();
		
		Assertions.assertEquals(3, responseEntity.getBody().size());
	}
	
	@Test
	void testGetTopTenHighestEarningEmployeeNamesNull() throws Exception{
		
		Mockito.when(
				employeeService.getTopNHighestEarningEmployeeNames(10)
			).thenReturn(new ArrayList<>());
		
		ResponseEntity<List<String>> responseEntity = employeeController.getTopTenHighestEarningEmployeeNames();
		
		Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}
	
	@Test
	void testGetTopTenHighestEarningEmployeeNamesException() throws Exception{
		
		Mockito.when(
				employeeService.getTopNHighestEarningEmployeeNames(10)
			).thenThrow(new Exception());
		
		ResponseEntity<List<String>> responseEntity = employeeController.getTopTenHighestEarningEmployeeNames();
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}
	
	@Test
	void testCreateEmployee() throws Exception{
		
		CreateEmployeeResponse actualValue = new CreateEmployeeResponse("1","Gaurav",1,1);
		HashMap<String, Object> employeeInput = new HashMap<>();
		Mockito.when(
				employeeService.createEmployee(employeeInput)
			).thenReturn(actualValue);
		
		ResponseEntity<CreateEmployeeResponse> responseEntity = employeeController.createEmployee(employeeInput);
		
		Assertions.assertEquals("Gaurav", responseEntity.getBody().getName());
		Assertions.assertEquals("1", responseEntity.getBody().getId());
		Assertions.assertEquals(1, responseEntity.getBody().getAge());
		Assertions.assertEquals(1, responseEntity.getBody().getSalary());
	}
	
	@Test
	void testCreateEmployeeException() throws Exception{
		
		HashMap<String, Object> employeeInput = new HashMap<>();
		Mockito.when(
				employeeService.createEmployee(employeeInput)
			).thenThrow(new Exception());
		
		ResponseEntity<CreateEmployeeResponse> responseEntity = employeeController.createEmployee(employeeInput);
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
	}

}
