package com.library.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.models.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
