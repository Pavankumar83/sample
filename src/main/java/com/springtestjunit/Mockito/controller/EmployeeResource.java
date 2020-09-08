package com.springtestjunit.Mockito.controller;

import com.springtestjunit.Mockito.dao.EmployeeRepository;
import com.springtestjunit.Mockito.model.Employee;
import com.springtestjunit.Mockito.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/addEmployee")
    public Response addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return new Response(employee.getId()+ " Inserted ",Boolean.TRUE);
    }
    @GetMapping("/getEmployee")
    public Response getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return new Response("records count"+employees.size(),Boolean.TRUE);
    }
}
