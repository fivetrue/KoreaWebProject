package com.insightkorea.korea.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.insightkorea.korea.model.Employee;


public interface EmployeeRepository extends Repository<Employee, Integer> {

	@Query("SELECT DISTINCT employee FROM Employee employee WHERE employee.name LIKE :name%")
	@Transactional(readOnly = true)
	Employee findByName(@Param("name") String name);
	
	@Query("SELECT DISTINCT employee FROM Employee employee WHERE employee.employeeId=:id")
	@Transactional(readOnly = true)
	Collection<Employee> findByEmployeeId(@Param("id") String employeeId);
	
	void save(Employee employee);
	
	
}
