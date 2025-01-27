package com.works.employeemanagement;

import com.works.employeemanagement.entities.Employee;
import com.works.employeemanagement.exception.ResourceNotFoundException;
import com.works.employeemanagement.repositories.EmployeeRepository;
import com.works.employeemanagement.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        employees.add(new Employee());

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> employeeList = employeeService.getAllEmployees();

        assertEquals(2, employeeList.size());
    }

    @Test
    void getEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee employeeById = employeeService.getEmployeeById(1L);
        assertEquals(1L, employeeById.getId());
    }

    @Test
    void getEmployeeByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(1L));
    }

    @Test
    void addEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);
        assertEquals(employee, result);
    }

    @Test
    void updateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1L);
        existingEmployee.setFirstName("John");
        existingEmployee.setLastName("Doe");

        Employee updateDetails = new Employee();
        updateDetails.setFirstName("Jane");
        updateDetails.setLastName("Doe");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Employee result = employeeService.updateEmployee(1L, updateDetails);
        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(employee);
    }


}
