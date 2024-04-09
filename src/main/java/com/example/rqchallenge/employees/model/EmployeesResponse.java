package com.example.rqchallenge.employees.model;

import java.util.List;

public class EmployeesResponse {

	List<Employee> data;
	String status;
	String message;
	public List<Employee> getData() {
		return data;
	}
	public void setData(List<Employee> data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
