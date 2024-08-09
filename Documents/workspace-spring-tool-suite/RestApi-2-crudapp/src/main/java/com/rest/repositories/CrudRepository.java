package com.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest.entites.Employee;

public interface CrudRepository extends JpaRepository<Employee, Long>
{
	
	   @Query(value = "SELECT * FROM employee WHERE id = :id", nativeQuery = true)
	    Employee findEmployeeById(@Param("id") long id);
	   
	  
	   
}
