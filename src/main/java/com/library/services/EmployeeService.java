package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.EmployeeRequest;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.EmployeeNotFoundException;
// import com.library.kafka.KafkaProducer;
import com.library.models.entities.Book;
import com.library.models.entities.Employee;
import com.library.models.repositories.BookRepository;
import com.library.models.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private BookRepository bookRepo;

    // @Autowired
    // private KafkaProducer kafkaProducer;

    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder().employeeName(employeeRequest.getEmployeeName())
                .dateCreated(employeeRequest.getDateCreated()).dateUpdated(employeeRequest.getDateUpdated()).build();

        // publish(employee);
        System.out.println(employeeRequest.getId());
        System.out.println(employeeRequest.getEmployeeName());
        System.out.println(employeeRequest.getDateCreated());
        System.out.println(employeeRequest.getDateUpdated());
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(employeeRequest.getId());

        if (employee.isPresent()) {
            employee.get().setEmployeeName(employeeRequest.getEmployeeName());
            employee.get().setDateUpdated(employeeRequest.getDateUpdated());

            return employeeRepo.save(employee.get());
        } else {
            throw new EmployeeNotFoundException("Employee with id " + employeeRequest.getId() + " not found");
        }

    }

    public Iterable<Employee> findAllEmployee() throws EmployeeNotFoundException {
        List<Employee> employee = employeeRepo.findAll();

        if (employee.size() <= 0) {
            throw new EmployeeNotFoundException("No one employee !!");
        } else {
            return employee;
        }
    }

    public Employee findOneEmployee(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        }
        throw new EmployeeNotFoundException("Employee with id " + id + " not found");
    }

    public void deleteEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepo.findById(id);

        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with id " + employee.get().getId() + " not found");
        } else {
            employeeRepo.deleteById(id);
        }
    }

    public Employee addBook(Long employeeId, Long bookId) throws EmployeeNotFoundException, BookNotFoundException {
        Employee employee = employeeRepo.findById(employeeId).get();
        Book book = bookRepo.findById(bookId).get();

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found");
        } else if (book == null) {
            throw new BookNotFoundException("Book with id " + bookId + " not found");
        } else {
            employee.addBook(book);
            return employeeRepo.save(employee);
        }
    }

    // public String publish(Employee employee) {
    // kafkaProducer.sendMessage(employee);
    // return "Json message sent to kafka topic";
    // }

    public String tes(EmployeeRequest employeeRequest) {
        return employeeRequest.toString();
    }
}
