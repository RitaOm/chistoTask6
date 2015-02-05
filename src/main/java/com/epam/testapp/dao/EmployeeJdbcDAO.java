package com.epam.testapp.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.epam.testapp.bean.*;

import static com.epam.testapp.dao.constants.FieldName.*;
import static com.epam.testapp.dao.constants.TokenHolder.*;

public class EmployeeJdbcDAO implements IEmployeeDAO {

	private DataSource dataSource;
	private String employeesQuery;
	private String workplacesQuery;
	//private String employeesNumberQuery;

	public EmployeeJdbcDAO() throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		String path = EmployeeJdbcDAO.class.getResource(QUERIES_XML_REL_PATH)
				.getPath();
		Document document = builder.build(new File(path));
		Element root = document.getRootElement();
		employeesQuery = root.getChildText(GET_EMPLOYEE_LIST_QUERY);
		workplacesQuery = root.getChildText(GET_WORKPLACES_QUERY);
		//employeesNumberQuery = root.getChildText(GET_EMPLOYEE_NUMBER_QUERY);
	}

	@Override
	public List<Employee> getEmployeeList(int firstResult, int resultsPerPage)
			throws SQLException {
		Connection connection = dataSource.getConnection();
		LinkedHashMap<Integer, Employee> employeeMap = new LinkedHashMap<Integer, Employee>();
		String fullWorkplacesQuery = completeWorkplacesQuery(workplacesQuery, resultsPerPage);
		try (PreparedStatement employeesSt = connection
				.prepareStatement(employeesQuery);
				PreparedStatement workplacesSt = connection
						.prepareStatement(fullWorkplacesQuery)) {
			employeesSt.setInt(1, firstResult);
			employeesSt.setInt(2, firstResult + resultsPerPage - 1);
			ResultSet  employeeRs = employeesSt.executeQuery();
			while (employeeRs.next()) {
				Employee emp = getEmployee(employeeRs);
				employeeMap.put(emp.getId(), emp);
				workplacesSt.setInt(employeeRs.getRow(), emp.getId());
			}
			ResultSet  workplacesRs = workplacesSt.executeQuery();
			while (workplacesRs.next()) {
				Workplace workplace = getWorkplace(workplacesRs);
				Employee emp = employeeMap.get(workplacesRs.getInt(EMPLOYEE_ID.toString()));
				emp.getWorkplaces().add(workplace);
			}
		} finally {
			connection.close();
		}
		return new ArrayList<Employee>(employeeMap.values());
	}

	private String completeWorkplacesQuery(String query, int resultsPerPage) {
		StringBuilder sb = new StringBuilder(query);
		sb.append("(");
		for (int i = 0; i < resultsPerPage; i++) {
			sb.append("?");
			if (i != resultsPerPage - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt(EMPLOYEE_ID.toString()));
		emp.setFirstName(rs.getString(FIRST_NAME.toString()));
		emp.setLastName(rs.getString(LAST_NAME.toString()));
		Country country = new Country();
		country.setId(rs.getInt(COUNTRY_ID.toString()));
		country.setName(rs.getString(COUNTRY_NAME.toString()));
		City city = new City();
		city.setId(rs.getInt(CITY_ID.toString()));
		city.setName(rs.getString(CITY_NAME.toString()));
		Address address = new Address();
		address.setId(rs.getInt(ADDRESS_ID.toString()));
		address.setName(rs.getString(ADDRESS_NAME.toString()));
		city.setCountry(country);
		address.setCity(city);
		emp.setAddress(address);
		return emp;
	}

	private Workplace getWorkplace(ResultSet rs) throws SQLException {
		Workplace workplace = new Workplace();
		workplace.setId(rs.getInt(WORKPLACE_ID.toString()));
		Office office = new Office();
		office.setId(rs.getInt(OFFICE_ID.toString()));
		office.setEmployeesNumber(rs.getInt(NUM_OF_WORKERS.toString()));
		Country country = new Country();
		country.setId(rs.getInt(COUNTRY_ID.toString()));
		country.setName(rs.getString(COUNTRY_NAME.toString()));
		City city = new City();
		city.setId(rs.getInt(CITY_ID.toString()));
		city.setName(rs.getString(CITY_NAME.toString()));
		Address address = new Address();
		address.setId(rs.getInt(ADDRESS_ID.toString()));
		address.setName(rs.getString(ADDRESS_NAME.toString()));
		Company company = new Company();
		company.setId(rs.getInt(COMPANY_ID.toString()));
		company.setName(rs.getString(COMPANY_NAME.toString()));
		Position position = new Position();
		position.setId(rs.getInt(POSITION_ID.toString()));
		position.setName(rs.getString(POSITION_NAME.toString()));
		city.setCountry(country);
		address.setCity(city);
		office.setAddress(address);
		office.setCompany(company);
		workplace.setOffice(office);
		workplace.setPosition(position);
		return workplace;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
