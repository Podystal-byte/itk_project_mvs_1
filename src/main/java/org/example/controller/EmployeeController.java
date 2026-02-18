package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.projection.EmployeeProjection;
import org.example.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/home")
    public String home() {
        return "Добро пожаловать в систему управления сотрудниками!";
    }

    @PostMapping("/department")
    public Department createDepartment(@RequestBody String name) {
        return employeeService.createDepartment(name);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/department/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return employeeService.getDepartmentById(id);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeProjection> getAllEmployeesProjection(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/projection/depart")
    public List<EmployeeProjection> getAllEmployeesProjectionForDepart(@RequestBody Department department){
        return employeeService.getEmployeeProjectionByDepartment(department.getId());
    }
}
