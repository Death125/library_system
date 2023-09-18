package com.library.controllers;

import org.modelmapper.ModelMapper;
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

import com.library.dto.EmployeeData;
import com.library.dto.ResponseData;
import com.library.models.entities.Employee;
import com.library.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Employee>> create(@Valid @RequestBody EmployeeData employeeData, Errors errors) {
        ResponseData<Employee> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());

                responseData.setStatus(false);
                responseData.setPayload(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        }
        Employee employee = modelMapper.map(employeeData, Employee.class);

        responseData.setStatus(true);
        responseData.setPayload(employeeService.saveEmployee(employee));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData<Employee>> update(@Valid @RequestBody EmployeeData employeeData, Errors errors) {
        ResponseData<Employee> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());

                responseData.setStatus(false);
                responseData.setPayload(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
            }
        }
        Employee employee = modelMapper.map(employeeData, Employee.class);

        responseData.setStatus(true);
        responseData.setPayload(employeeService.updateEmployee(employee));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/findAllEmployee")
    public Iterable<Employee> findAllEmployee() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/{id}")
    public Employee findOneEmployeeById(@PathVariable("id") Long id) {
        return employeeService.findOneEmployee(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
    }
}