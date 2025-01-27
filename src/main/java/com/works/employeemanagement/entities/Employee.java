package com.works.employeemanagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String firstName;

    @NotEmpty(message = "LastName is required")
    @Size(min = 2, max = 100, message = "LastName should be between 2 and 100 characters")
    private String lastName;

    @NotEmpty(message = "Role is required")
    private String role;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

}
