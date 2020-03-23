package com.ayaz.empl.controller;

import com.ayaz.empl.dto.EmployeeDto;
import com.ayaz.empl.model.Employee;
import com.ayaz.empl.service.EmployeeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/employee")
@Controller
@Slf4j
public class EmployeeControler {

    private final EmployeeService employeeService;

    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "connection_error")
    })
    public ResponseEntity<List<Employee>> ListOfOwner(){
        List<Employee> employeeList = new ArrayList<>();
        Set<Employee> employees = employeeService.retrieveEmployees();
        employeeList.addAll(employees);
        return new ResponseEntity<>(employeeList, HttpStatus.OK) ;
    }
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "connection_error")
    })
    public ResponseEntity<EmployeeDto> saveEmployee(@Validated @RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(employeeDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedEmployee);
    }
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "connection_error")
    })
    public ResponseEntity updateEmployee(@PathVariable("id") Long id, @Validated @RequestBody  EmployeeDto employeeDto){
        if (id != employeeDto.getId()){
            throw new RuntimeException(" ID Not Found = " + employeeDto.getId() );
        }
        employeeService.updateEmployee(employeeDto , id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "connection_error")
    })
    public void deleteById(@PathVariable("id") Long id){
        employeeService.deleteById(id);
    }

}
