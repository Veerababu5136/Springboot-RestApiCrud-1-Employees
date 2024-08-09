package com.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.rest.entites.Employee;
import com.rest.services.CrudService;



/*
 
 Below is the implemetaion of crud operations in provider development
 
 Provider application is a distributed application
 
 Distributed application means which provides services to another applications.
 
 In provider applications there is no ui
 
 Provider application has only backend code
 
 Multiple consumers applications can access the service from provider application
 
 ........................................................................................
 
 @RestController annotation mapping makes our springboot application as distributed application
 
 @RequestBody will take json data from http request body/payload and converts into out obj
 
 ............................................................................................
 
 @GetMapping takes http-get-request from consumer and maps to our rest-methods, no body/payload from consumer
 
 @PostMapping maps post request, used to create data in server/provider side
 
 @PutMapping maps put request, used to update data in server/provider side
 
 @DeleteMapping maps delete request, used to delete data in server/provider side
 
 ............................................................................................
 
 
 ResponseEntity is a class which creates custom response codes
 
 Beacuse when data created and method has return value,status code is 200:OK
 
 But when data created and method has no return value,then also status code is 200:OK
 
 That's why we using custom status codes
 
 By defalut 200:OK ,all codes created by springboot
 

 ............................................................................................

 @RequestParam annotation mapping is used to get value from url to method as K-V pair
 
 Ex: localhost:8079/?name=amrutha_pavani
 
 
 @PathVariable annotation mapping is used to get value from url to method
 
 Ex: localhost:8079/amrutha_pavani
 
 
 ..............................................................................................
 
 
 swagger-ui provides documentation for the springboot distributed application
 
 In this all methods get ui 
 
 In before this postman was usen for testing urls 
 
 But swagger shows all urls with input/output/validation/testing.

 
 */
@RestController
public class CrudController 
{
	
	@Autowired
	private CrudService crudService;
	
	
	public CrudService getCrudService() {
		return crudService;
	}


	public void setCrudService(CrudService crudService) {
		this.crudService = crudService;
	}
	
	
	

	@GetMapping("/employee")
	public ResponseEntity<?> getAll() 
	{
	    try 
	    {
	        List<Employee> employees = crudService.getAllEmployees();

	        if (employees == null || employees.isEmpty()) 
	        {
	            return new ResponseEntity<>("Employees not found,",HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(employees, HttpStatus.OK);
	        
	    } 
	    catch (Exception e) 
	    {
	        return new ResponseEntity<>("An error occurred while retrieving employees: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") long id) {
	    try {
	        Employee employee = crudService.getEmployeeById(id);

	        if (employee == null) {
	            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(employee, HttpStatus.OK);
	        
	    } catch (Exception e) {
	        return new ResponseEntity<>("An error occurred while retrieving employee details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	
	@PostMapping("/employee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
	{
		try
		{
		boolean added=crudService.addEmployee(employee);
		
		if(added)
		{
			return new ResponseEntity<>("Employee adding successful",HttpStatus.CREATED);
		}
        return new ResponseEntity<>("Employee adding unsuccessful", HttpStatus.BAD_REQUEST);
        
		}
		catch (Exception e) 
		{
	        return new ResponseEntity<>("An error occurred while adding the employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @PutMapping("/employee")
	    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee)
	 {
	        try {
	            boolean updated = crudService.updateEmployee(employee);

	            if (updated) {
	                return new ResponseEntity<>("Employee update successful", HttpStatus.OK);
	            }

	            return new ResponseEntity<>("Employee update unsuccessful", HttpStatus.BAD_REQUEST);
	            
	        } 
	        catch (Exception e) 
	        {
	            return new ResponseEntity<>("An error occurred while updating the employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	 }
	    
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") long id)
	{
		try
		{
		boolean deleted=crudService.deleteEmployeeById(id);
		
		if(deleted)
		{
			return new ResponseEntity<>("Employee deleted successfully",HttpStatus.OK);
		}
		else
		{
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
		}
		}
		
		catch (Exception e) 
		{
			// TODO: handle exception
			
			return new ResponseEntity<>("Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	
	

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex)
	{
        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
