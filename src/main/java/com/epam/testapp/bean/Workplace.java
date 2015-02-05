package com.epam.testapp.bean;

import java.io.Serializable;

public class Workplace implements Serializable {

	private static final long serialVersionUID = 3316724055974367133L;
	private int id;
	private Office office;
	private Employee employee;
	private Position position;

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
