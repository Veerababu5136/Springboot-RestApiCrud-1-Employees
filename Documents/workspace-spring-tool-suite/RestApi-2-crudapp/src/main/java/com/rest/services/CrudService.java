package com.rest.services;

import java.util.List;

import com.rest.entites.Employee;

public interface CrudService 
{
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id);
	
	boolean addEmployee(Employee employee);
	
	boolean updateEmployee(Employee employee);
	
	boolean deleteEmployeeById(long id);


}
