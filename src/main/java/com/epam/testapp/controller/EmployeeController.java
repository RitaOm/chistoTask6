package com.epam.testapp.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.testapp.bean.Employee;
import com.epam.testapp.dao.IEmployeeDAO;

@Controller
public class EmployeeController {

	private static final Logger LOG = Logger
			.getLogger(EmployeeController.class);
	private IEmployeeDAO dao;

	@RequestMapping(value = "/employeeList", method = RequestMethod.GET)
	public ModelAndView categories(ModelAndView model) {
		List<Employee> list;
		try {
			list = dao.getEmployeeList(1, 100);
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			model.setViewName(Constants.ERROR);
			return model;
		}
		model.setViewName(Constants.EMPLOYEE_LIST);
		model.addObject(Constants.EMPLOYEE_LIST, list);
		return model;
	}

	public IEmployeeDAO getDao() {
		return dao;
	}

	public void setDao(IEmployeeDAO dao) {
		this.dao = dao;
	}
	
}
