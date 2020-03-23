package com.ayaz.empl.service;

import com.ayaz.empl.dto.EmployeeDto;
import com.ayaz.empl.mapper.EmployeeMapper;
import com.ayaz.empl.model.Employee;
import com.ayaz.empl.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Set<Employee> retrieveEmployees() {
        Set<Employee> employees = new HashSet<>();
        employeeRepository.findAll().iterator().forEachRemaining(employees::add);
        return employees;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee newEmp = employeeMapper.toEmployee(employeeDto);
        Employee savedEmp = employeeRepository.save(newEmp);
        return employeeMapper.toEmployeeDto(savedEmp)  ;
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employe can'not be found"));
        employeeMapper.copyToEmployee(employeeDto, existingEmployee);
        employeeRepository.save(existingEmployee);
    }
}
