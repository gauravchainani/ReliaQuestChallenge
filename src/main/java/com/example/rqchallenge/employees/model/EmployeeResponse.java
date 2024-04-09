package com.example.rqchallenge.employees.model;

public class EmployeeResponse {
	Employee data;
	String status;
	String message;
	public Employee getData() {
		return data;
	}
	public void setData(Employee data) {
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
