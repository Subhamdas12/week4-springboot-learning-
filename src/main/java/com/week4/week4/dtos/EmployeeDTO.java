package com.week4.week4.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private String name;

    private String email;
    private String role;

    private Integer age;

    private Double salary;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
