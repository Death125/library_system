package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.EmployeeRequest;
import com.library.exceptions.EmployeeNotFoundException;
import com.library.models.entities.Employee;
import com.library.models.repositories.EmployeeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder().employee_name(employeeRequest.getEmployee_name())
                .dateCreated(employeeRequest.getDateCreated()).dateUpdated(employeeRequest.getDateUpdated()).build();

        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(employeeRequest.getId());

        if (employee.isPresent()) {
            employee.get().setEmployee_name(employeeRequest.getEmployee_name());
            employee.get().setDateUpdated(employeeRequest.getDateUpdated());

            return employeeRepo.save(employee.get());
        } else {
            throw new EmployeeNotFoundException("Employee with id " + employee + " not found");
        }

    }

    public Iterable<Employee> findAllEmployee() throws EmployeeNotFoundException {
        List<Employee> employee = employeeRepo.findAll();

        if (employee == null) {
            throw new EmployeeNotFoundException("No one employee !!");
        } else {
            return employee;
        }
    }

    public Employee findOneEmployee(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " + employee + " not found");
        }
        return employee.get();
    }

    public void deleteEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " + employee + " not found");
        } else {
            employeeRepo.deleteById(id);
        }
    }
}
