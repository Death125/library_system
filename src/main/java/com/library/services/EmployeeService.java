package com.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.models.entities.Employee;
import com.library.models.repos.EmployeeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Iterable<Employee> findAllEmployee() {
        return employeeRepo.findAll();
    }

    public Employee findOneEmployee(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (!employee.isPresent()) {
            return null;
        }
        return employee.get();
    }

    public void deleteEmployeeById(Long id) {
        employeeRepo.deleteById(id);
    }
}
