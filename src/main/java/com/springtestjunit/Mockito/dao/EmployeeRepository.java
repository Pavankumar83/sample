package com.springtestjunit.Mockito.dao;

import com.springtestjunit.Mockito.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
