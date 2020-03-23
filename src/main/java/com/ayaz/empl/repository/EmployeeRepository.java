package com.ayaz.empl.repository;

import com.ayaz.empl.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee , Long> {

}
