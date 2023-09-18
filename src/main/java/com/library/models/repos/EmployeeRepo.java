package com.library.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.library.models.entities.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
