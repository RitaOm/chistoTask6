package com.epam.testapp.bean;

import java.util.HashSet;
import java.util.Set;

public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private Set<Workplace> workplaces = new HashSet<Workplace>();
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<Workplace> getWorkplaces() {
		return workplaces;
	}
	public void setWorkplaces(Set<Workplace> workplaces) {
		this.workplaces = workplaces;
	}

}
