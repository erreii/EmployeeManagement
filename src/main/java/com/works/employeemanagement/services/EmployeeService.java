package com.works.employeemanagement.services;

import com.works.employeemanagement.entities.Employee;
import com.works.employeemanagement.exception.ResourceNotFoundException;
import com.works.employeemanagement.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    final EmployeeRepository employeeRepository;

    /**
     * Get all employee
     *
     * @return
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Find Employee by ID
     *
     * @param id
     * @return
     */
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    /**
     * Add a new employee (Find if exist)
     *
     * @param employee
     * @return
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Update employee by ID with Employee Details
     *
     * @param id
     * @param employeeDetails
     * @return
     */
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee not found with id " + id));
        if (employee != null) {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setRole(employeeDetails.getRole());
            employee.setEmail(employeeDetails.getEmail());
            return employeeRepository.save(employee);
        }
        return null;
    }

    /**
     * Delete Employee By Id
     * @param id
     * @return
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
