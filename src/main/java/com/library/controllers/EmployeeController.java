package com.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.EmployeeRequest;
import com.library.exceptions.EmployeeNotFoundException;
import com.library.models.entities.Employee;
import com.library.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<Employee>(employeeService.createEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeRequest));
    }

    @GetMapping("/findAllEmployee")
    public Iterable<Employee> findAllEmployee() throws EmployeeNotFoundException {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/findOneEmployee/{id}")
    public Employee findOneEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return employeeService.findOneEmployee(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.deleteEmployeeById(id);
    }
}
