package com.service3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.service3.model.Employee;



public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	Employee findByName(String name);

}