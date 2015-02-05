package com.epam.testapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.epam.testapp.bean.Employee;

public interface IEmployeeDAO {
	
	public List<Employee> getEmployeeList(int firstResult, int resultsPerPage) throws SQLException;
	
}
