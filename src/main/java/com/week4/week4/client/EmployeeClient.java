package com.week4.week4.client;

import java.util.List;

import com.week4.week4.dtos.EmployeeDTO;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);
}
