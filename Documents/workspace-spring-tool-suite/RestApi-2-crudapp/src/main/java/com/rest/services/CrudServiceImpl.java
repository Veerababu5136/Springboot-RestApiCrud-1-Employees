package com.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.entites.Employee;
import com.rest.repositories.CrudRepository;

@Service
public class CrudServiceImpl implements CrudService {

	
	@Autowired
	private CrudRepository crudRepository;
	
	
	public CrudRepository getCrudRepository() {
		return crudRepository;
	}

	public void setCrudRepository(CrudRepository crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		
		
		return crudRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		return crudRepository.findEmployeeById(id);
	}

	@Override
	public boolean addEmployee(Employee employee) 
	{
		Employee added=crudRepository.save(employee);
		
		if(added==null)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteEmployeeById(long id) 
	{
        if (crudRepository.existsById(id))
        {
        	crudRepository.deleteById(id);
        	return true;
        }
        
        return false;
	}

	@Override
	 public boolean updateEmployee(Employee employee) 
	{
        if (crudRepository.existsById(employee.getId())) 
        {
            crudRepository.save(employee);
            return true;
        }
        return false; // Update unsuccessful if employee not found
    }
}
