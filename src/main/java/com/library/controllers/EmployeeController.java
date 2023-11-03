package com.library.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.EmployeeRequest;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.EmployeeNotFoundException;
import com.library.models.entities.Employee;
import com.library.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
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
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping("/findAllEmployee")
    public ResponseEntity<Iterable<Employee>> findAllEmployee() throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.findAllEmployee());
    }

    @GetMapping("/findOneEmployee/{id}")
    public ResponseEntity<Employee> findOneEmployee(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.findOneEmployee(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{employeeId}/addBook/{bookId}")
    public ResponseEntity<Employee> addBook(@PathVariable("employeeId") Long employeeId, @PathVariable Long bookId)
            throws EmployeeNotFoundException, BookNotFoundException {
        return ResponseEntity.ok(employeeService.addBook(employeeId, bookId));
    }

    @GetMapping("/ts")
    public String ts() {
        EmployeeRequest employeeRequest = new EmployeeRequest(2L, "bubu", LocalDateTime.now(), LocalDateTime.now());
        return employeeService.tes(employeeRequest);
    }
}
