package com.week4.week4.client.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.week4.week4.advices.ApiResponse;
import com.week4.week4.client.EmployeeClient;
import com.week4.week4.dtos.EmployeeDTO;
import com.week4.week4.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retreve all employees in getAllEmployees");
        try {
            log.info("Attempt to call the restClient Method in getAllEmployees");
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get().uri("employee").retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Couldnot create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            log.debug("Successfully retrived the employee in getAllEmployees");
            log.trace("Retrived employee list in getAllEmployees : {}", employeeDTOList.getData());

            return employeeDTOList.getData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get employee by id in getEmployeeById with id : {}", employeeId);
        try {
            ApiResponse<EmployeeDTO> employeeDTO = restClient.get()
                    .uri("employee/{employeeId}", employeeId).retrieve()

                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Couldnot create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrived the employee in getEmployeeById");
            log.trace("Retrived employee from getEmployeeById : {}", employeeDTO.getData());
            return employeeDTO.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create an employee with information : {}", employeeDTO);
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post().uri("employee")
                    .body(employeeDTO).retrieve().onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClient error occured during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Couldnot create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully created employee in createNewEmployee");
            log.trace("Successfully created a new employee : {}", employeeDTOApiResponse.getBody().getData());
            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
