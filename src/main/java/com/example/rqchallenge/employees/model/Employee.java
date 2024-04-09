package com.example.rqchallenge.employees.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("id")
	private String id;
	@JsonProperty("employee_name")
	private String name;
	@JsonProperty("employee_salary")
	private Integer salary;
	@JsonProperty("employee_age")
	private Integer age;
	@JsonProperty("profile_image")
	private String profileImage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	
}
