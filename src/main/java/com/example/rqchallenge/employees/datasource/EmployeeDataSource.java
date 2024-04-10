package com.example.rqchallenge.employees.datasource;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class EmployeeDataSource {
	
	@Value("${employeeurl.baseurl}")
	private String baseurl;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Retry(name = "getCall")
	public <T> T getCall(String url, Class<T> classType){
		return restTemplate().getForObject(url, classType);
	}

	@Retry(name = "postCall")
	public <T> T postCall(String url, Object request, Class<T> classType){
		return restTemplate().postForObject(url, request, classType);
	}

	@Retry(name = "deleteCall")
	public void deleteCall(String url){
		restTemplate().delete(url);
	}

	public String getAllEmployees() {
		String url = baseurl + "employees";
		try{
			return getCall(url, String.class);
		} catch (Exception e){
			log.error("Error when calling REST API " + url, e);
			return getAllEmployeesMockData();
		}
	}
	
	public String getEmployeeById(String id) throws RestClientException {
		String url = baseurl + "employee/" + id;
		try{
			return getCall(url, String.class);
		} catch (Exception e){
			log.error("Error when calling REST API " + url, e);
			return getEmployeeByIdMockdata();
		}
	}

	public String createEmployee(Object request) throws RestClientException{
		String url = baseurl + "create/";
		try {
			return postCall(url, request, String.class);
		} catch (Exception e) {
			log.error("Error when calling REST API : " + url, e);
			return createEmployeeMockData();
		}
	}

	public void deleteEmployee(String id) throws RestClientException{
		String url = baseurl + "delete/" + id;
		try {
			deleteCall(url);
		} catch (Exception e) {
			log.error("Error when calling REST API : " + url, e);
		}
	}

	public static String getAllEmployeesMockData(){
		log.info("Returning Mock data for getAllEmployees");
		return "{\"status\":\"success\",\"data\":[{\"id\":1,\"employee_name\":\"TigerNixon\",\"employee_salary\":320800,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":2,\"employee_name\":\"GarrettWinters\",\"employee_salary\":170750,\"employee_age\":63,\"profile_image\":\"\"},{\"id\":3,\"employee_name\":\"AshtonCox\",\"employee_salary\":86000,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":4,\"employee_name\":\"CedricKelly\",\"employee_salary\":433060,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":5,\"employee_name\":\"AiriSatou\",\"employee_salary\":162700,\"employee_age\":33,\"profile_image\":\"\"},{\"id\":6,\"employee_name\":\"BrielleWilliamson\",\"employee_salary\":372000,\"employee_age\":61,\"profile_image\":\"\"},{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":8,\"employee_name\":\"RhonaDavidson\",\"employee_salary\":327900,\"employee_age\":55,\"profile_image\":\"\"},{\"id\":9,\"employee_name\":\"ColleenHurst\",\"employee_salary\":205500,\"employee_age\":39,\"profile_image\":\"\"},{\"id\":10,\"employee_name\":\"SonyaFrost\",\"employee_salary\":103600,\"employee_age\":23,\"profile_image\":\"\"},{\"id\":11,\"employee_name\":\"JenaGaines\",\"employee_salary\":90560,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":12,\"employee_name\":\"QuinnFlynn\",\"employee_salary\":342000,\"employee_age\":22,\"profile_image\":\"\"},{\"id\":13,\"employee_name\":\"ChardeMarshall\",\"employee_salary\":470600,\"employee_age\":36,\"profile_image\":\"\"},{\"id\":14,\"employee_name\":\"HaleyKennedy\",\"employee_salary\":313500,\"employee_age\":43,\"profile_image\":\"\"},{\"id\":15,\"employee_name\":\"TatyanaFitzpatrick\",\"employee_salary\":385750,\"employee_age\":19,\"profile_image\":\"\"},{\"id\":16,\"employee_name\":\"MichaelSilva\",\"employee_salary\":198500,\"employee_age\":66,\"profile_image\":\"\"},{\"id\":17,\"employee_name\":\"PaulByrd\",\"employee_salary\":725000,\"employee_age\":64,\"profile_image\":\"\"},{\"id\":18,\"employee_name\":\"GloriaLittle\",\"employee_salary\":237500,\"employee_age\":59,\"profile_image\":\"\"},{\"id\":19,\"employee_name\":\"BradleyGreer\",\"employee_salary\":132000,\"employee_age\":41,\"profile_image\":\"\"},{\"id\":20,\"employee_name\":\"DaiRios\",\"employee_salary\":217500,\"employee_age\":35,\"profile_image\":\"\"},{\"id\":21,\"employee_name\":\"JenetteCaldwell\",\"employee_salary\":345000,\"employee_age\":30,\"profile_image\":\"\"},{\"id\":22,\"employee_name\":\"YuriBerry\",\"employee_salary\":675000,\"employee_age\":40,\"profile_image\":\"\"},{\"id\":23,\"employee_name\":\"CaesarVance\",\"employee_salary\":106450,\"employee_age\":21,\"profile_image\":\"\"},{\"id\":24,\"employee_name\":\"DorisWilder\",\"employee_salary\":85600,\"employee_age\":23,\"profile_image\":\"\"}],\"message\":\"Successfully!Allrecordshasbeenfetched.\"}";
	}

	public static String getEmployeeByIdMockdata(){
		log.info("Returning mock data for getEmployeeById");
		return "{\"status\":\"success\",\"data\":{\"id\":7,\"employee_name\":\"HerrodChandler\",\"employee_salary\":137500,\"employee_age\":59,\"profile_image\":\"\"},\"message\":\"Successfully!Recordhasbeenfetched.\"}";
	}

	public static String createEmployeeMockData(){
		log.info("Returning mock data for createEmployee");
		return "{\"status\":\"success\",\"data\":{\"name\":\"Gaurav\",\"salary\":\"800000\",\"age\":\"34\",\"id\":7494},\"message\":\"Successfully!Recordhasbeenadded.\"}";
	}
}
