package com.ayaz.empl.service;

import com.ayaz.empl.dto.EmployeeDto;
import com.ayaz.empl.model.Employee;

import java.util.Set;

public interface EmployeeService {
    Set<Employee> retrieveEmployees();
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    void deleteById(Long id);
    void updateEmployee(EmployeeDto employeeDto, Long id);
}
