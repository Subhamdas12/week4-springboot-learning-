package com.week4.week4;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.week4.week4.client.EmployeeClient;
import com.week4.week4.dtos.EmployeeDTO;

@SpringBootTest
class Week4ApplicationTests {
	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployees() {
		List<EmployeeDTO> employeeListDTO = employeeClient.getAllEmployees();
		System.out.println(employeeListDTO);
	}

	@Test
	@Order(2)
	void getEmployeeById() {
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);

	}

	@Test
	@Order(1)
	void createNewEmployee() {
		EmployeeDTO employeeDTO = new EmployeeDTO("Anuj", "anuj@gmail.com", "ADMIN",
				19, 5000.0, LocalDate.of(2020, 12, 1), true);
		EmployeeDTO savedEmployeeDTO = employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}

}
