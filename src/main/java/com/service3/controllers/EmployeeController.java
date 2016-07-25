package com.service3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.service3.model.Employee;
import com.service3.repositories.EmployeeRepository;
import com.service3.services.EmployeeService;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired 
	private EmployeeRepository employeeRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee returnEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployee(id);
		return employee;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> addEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		
		if (employeeService.isEmployeeExist(employee)) {
			System.out.println("exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        employeeService.saveEmployee(employee);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}

