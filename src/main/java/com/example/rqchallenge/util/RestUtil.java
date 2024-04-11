package com.example.rqchallenge.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestUtil {
	
	@Bean
	public static RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Retry(name = "getCall")
	public static <T> T getCall(String url, Class<T> classType){
		log.error("Calling REST API : " + url);
		return restTemplate().getForObject(url, classType);
	}

	@Retry(name = "postCall")
	public static <T> T postCall(String url, Object request, Class<T> classType){
		log.error("Calling REST API : " + url);
		return restTemplate().postForObject(url, request, classType);
	}

	@Retry(name = "deleteCall")
	public static void deleteCall(String url){
		log.error("Calling REST API : " + url);
		restTemplate().delete(url);
	}

}
