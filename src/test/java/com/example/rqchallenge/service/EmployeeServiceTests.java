package com.example.rqchallenge.service;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;

import com.example.rqchallenge.employees.model.CreateEmployeeResponse;
import com.example.rqchallenge.employees.model.Employee;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.example.rqchallenge.util.RestUtil;

@SpringBootTest
public class EmployeeServiceTests {

MockedStatic<RestUtil> restUtil;
	
    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void init() {
    	restUtil = Mockito.mockStatic(RestUtil.class);
    }
    
    @AfterEach
    void destroy() {
    	restUtil.close();
    }
    
    @Test
    void testGetAllEmployees() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon1\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);

		List<Employee> employeeList = employeeService.getAllEmployees();

        // Actual Data doesnt have _mockData appended in the name of the employee for id 1
        Assertions.assertEquals(
        		"Tiger Nixon1",
                employeeList
                .stream()
                .filter(employee -> employee.getId().equals("1"))
                .map(employee -> employee.getName())
                .findFirst()
                .get()
            
        );
    }
    
    @Test
    void testGetAllEmployeesRestApiError() throws Exception{
    	
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenThrow(new RestClientException(""));

		List<Employee> employeeList = employeeService.getAllEmployees();

        // In case of API exception , mock data is expected return
        Assertions.assertEquals(
        		"Tiger Nixon_mockData",
                employeeList
                .stream()
                .filter(employee -> employee.getId().equals("1"))
                .map(employee -> employee.getName())
                .findFirst()
                .get()
            
        );
    }
    
    @Test
    void testGetAllEmployeesEmptyData() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":[],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
       
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);

		List<Employee> employeeList = employeeService.getAllEmployees();

        // In case of empty data, employee list should be 0
        Assertions.assertEquals(
        		0,
                employeeList.size()
        );
    }
    
    @Test
    void testGetAllEmployeesNoDataField() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
       
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);

		List<Employee> employeeList = employeeService.getAllEmployees();

        // In case of no data field, employee list should be null
        Assertions.assertEquals(
        		null,
                employeeList
        );
    }
    
    @Test
    void testGetAllEmployeesJsonParsingError() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_names\":\"Tiger Nixon1\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);

        // In case of Parsing exception , Exception is delegated.
		Assertions.assertThrows(Exception.class, () -> employeeService.getAllEmployees());
    }
    
    @Test
    void testGetEmployeeById() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},\"message\":\"Successfully!Recordhasbeenfetched.\"}";
    	
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employee/7",
                    String.class
                ))
                .thenReturn(restApiResponse);

		Employee employee = employeeService.getEmployeeById("7");

        // Actual Data doesnt have _mockData appended in the name of the employee for id 7
        Assertions.assertEquals(
        		"HerrodChandler",
                employee.getName()
            
        );
    }
    
    @Test
    void testGetEmployeeByIdRestApiError() throws Exception{
    	
        restUtil.when(
                () -> RestUtil.getCall(
                		 "https://dummy.restapiexample.com/api/v1/employee/7",
                    String.class
                ))
                .thenThrow(new RestClientException(""));

        Employee employee = employeeService.getEmployeeById("7");

        // In case of API exception , mock data is expected return
        Assertions.assertEquals(
        		"HerrodChandler_mockData",
                employee.getName()
            
        );
    }
    
    @Test
    void testGetEmployeeByIdEmptyData() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":{},\"message\":\"Successfully!Recordhasbeenfetched.\"}";
    
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employee/7",
                    String.class
                ))
                .thenReturn(restApiResponse);

    	Employee employee = employeeService.getEmployeeById("7");

        // In case of empty data, employee list should be 0
        Assertions.assertEquals(
        		null,
        		employee.getName()
        );
    }
    
    @Test
    void testGetEmployeeByIdNoDataField() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"message\":\"Successfully!Recordhasbeenfetched.\"}";
       
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employee/7",
                    String.class
                ))
                .thenReturn(restApiResponse);

        Employee employee = employeeService.getEmployeeById("7");

        // In case of no data field, employee list should be null
        Assertions.assertEquals(
        		null,
        		employee
        );
    }
    
    @Test
    void testGetEmployeeByIdJsonParsingError() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":{\"id\":7,\"employee_names\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},\"message\":\"Successfully!Recordhasbeenfetched.\"}";
    	
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employee/7",
                    String.class
                ))
                .thenReturn(restApiResponse);

        // In case of Parsing exception , Exception is delegated.
		Assertions.assertThrows(Exception.class, () -> employeeService.getAllEmployees());
    }
    
    @Test
    void testGetEmployeesByNameSearch() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon1\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
        
        List<Employee> employeeList = employeeService.getEmployeesByNameSearch("Tiger");
        
        Assertions.assertEquals(employeeList.size(), 1);

    }
    
    @Test
    void testGetEmployeesByNameSearchNoMatch() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon1\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
        
        List<Employee> employeeList = employeeService.getEmployeesByNameSearch("xxxx");
        
        Assertions.assertEquals(employeeList.size(), 0);

    }
    
    @Test
    void testGetEmployeesByNameNoDataField() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);

        List<Employee> employeeList = employeeService.getEmployeesByNameSearch("xxxx");
        // In case of Parsing exception , Exception is delegated.
        Assertions.assertEquals(
        		null,
        		employeeList
        );

    }
    
    @Test
    void testGetHighestSalary() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon1\",\"employee_salary\":10000000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
        restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
        Integer higestSalary = employeeService.getHighestSalary();
        Assertions.assertEquals(
        		10000000,
        		higestSalary
        );
    }
    
    @Test
    void testGetHighestSalaryNoDataField() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
        Integer higestSalary = employeeService.getHighestSalary();
        Assertions.assertEquals(
        		0,
        		higestSalary
        );
    }
    
    @Test
    void testGetTopNHighestEarningEmployeeNames() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon1\",\"employee_salary\":10000000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
    	List<String> employeeList = employeeService.getTopNHighestEarningEmployeeNames(10);
    	Assertions.assertEquals(
        		"Tiger Nixon1",
        		employeeList.get(0)
        );
    	Assertions.assertEquals(
        		"CedricKelly",
        		employeeList.get(4)
        );
    	Assertions.assertEquals(
        		"RhonaDavidson",
        		employeeList.get(9)
        );
    }
    
    @Test
    void testGetTopNHighestEarningEmployeeNamesNoDataField() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
    	
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
    	List<String> employeeList = employeeService.getTopNHighestEarningEmployeeNames(10);
    	Assertions.assertEquals(
        		null,
        		employeeList
        );
    }
    
    @Test
    void testGetTopNHighestEarningEmployeeNamesLessThan10Employees() throws Exception{
    	String restApiResponse = "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"Tiger Nixon1\",\"employee_salary\":10000000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
    	
    	restUtil.when(
                () -> RestUtil.getCall(
                    "https://dummy.restapiexample.com/api/v1/employees",
                    String.class
                ))
                .thenReturn(restApiResponse);
    	List<String> employeeList = employeeService.getTopNHighestEarningEmployeeNames(10);
    	Assertions.assertEquals(
        		4,
        		employeeList.size()
        );
    }
    
    @Test
    void testCreateEmployee() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":{\"name\":\"Gaurav\",\"salary\":\"800000\",\"age\":\"34\",\"id\":7494},\"message\":\"Successfully!Recordhasbeenadded.\"}";
    	HashMap<String, Object> employeeData = new HashMap<String, Object>();
    	restUtil.when(
                () -> RestUtil.postCall(
                		"https://dummy.restapiexample.com/api/v1/create",
                		employeeData,
                    String.class
                ))
                .thenReturn(restApiResponse);

    	CreateEmployeeResponse employee = employeeService.createEmployee(employeeData);

        Assertions.assertEquals(
        		"Gaurav",
                employee.getName()
            
        );
    }
    
    @Test
    void testCreateEmployeeRestApiError() throws Exception{
    	
        HashMap<String, Object> employeeData = new HashMap<String, Object>();
    	restUtil.when(
                () -> RestUtil.postCall(
                		"https://dummy.restapiexample.com/api/v1/create",
                		employeeData,
                    String.class
                ))
    	.thenThrow(new RestClientException(""));

    	CreateEmployeeResponse employee = employeeService.createEmployee(employeeData);

        // In case of API exception , mock data is expected return
        Assertions.assertEquals(
        		"Gaurav_mockData",
                employee.getName()
         
            
        );
    }
    
    @Test
    void testCreateEmployeeEmptyData() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":{},\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
       
    	HashMap<String, Object> employeeData = new HashMap<String, Object>();
    	restUtil.when(
                () -> RestUtil.postCall(
                		"https://dummy.restapiexample.com/api/v1/create",
                		employeeData,
                    String.class
                ))
                .thenReturn(restApiResponse);

    	CreateEmployeeResponse employee = employeeService.createEmployee(employeeData);

        // In case of empty data, employee name should be null
        Assertions.assertEquals(
        		null,
                employee.getName()
        );
    }
    
    @Test
    void testCreateEmployeeNoDataField() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
    	HashMap<String, Object> employeeData = new HashMap<String, Object>();
    	restUtil.when(
                () -> RestUtil.postCall(
                		"https://dummy.restapiexample.com/api/v1/create",
                		employeeData,
                    String.class
                ))
                .thenReturn(restApiResponse);

    	CreateEmployeeResponse employee = employeeService.createEmployee(employeeData);

        // In case of no data field, employee should be null
        Assertions.assertEquals(
        		null,
                employee
        );
    }
    
    @Test
    void testCreateEmployeeJsonParsingError() throws Exception{
    	
    	String restApiResponse = "{\"status\":\"success\",\"data\":{\"names\":\"Gaurav\",\"salary\":\"800000\",\"age\":\"34\",\"id\":7494},\"message\":\"Successfully!Recordhasbeenadded.\"}";
    	HashMap<String, Object> employeeData = new HashMap<String, Object>();
    	restUtil.when(
                () -> RestUtil.postCall(
                		"https://dummy.restapiexample.com/api/v1/create",
                		employeeData,
                    String.class
                ))
                .thenReturn(restApiResponse);

        // In case of Parsing exception , Exception is delegated.
		Assertions.assertThrows(Exception.class, () -> employeeService.createEmployee(employeeData));
    }
}
