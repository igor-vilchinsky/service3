package com.service3.services;

import com.service3.model.Employee;

public interface EmployeeService {
	
	Employee getEmployee(Long id);
	
	void saveEmployee(Employee employee);

	boolean isEmployeeExist(Employee employee);

}