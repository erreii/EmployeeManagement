package com.works.employeemanagement.controllers;

import com.works.employeemanagement.entities.Employee;
import com.works.employeemanagement.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeRestController {

    final EmployeeService employeeService;

    /**
     * Get All Employees Rest endpoint
     *
     * @return
     */
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Get Employee By ID Rest endpoint
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    /**
     * Add Employee Rest endpoint
     *
     * @param employee
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    /**
     * Update Employee Rest endpoint
     *
     * @param id
     * @param employeeDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Delete Employee Rest endpoint
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
