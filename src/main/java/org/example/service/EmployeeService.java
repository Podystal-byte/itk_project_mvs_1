package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.projection.EmployeeProjection;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        return departmentRepository.save(department);
    }

    public Employee createEmployee(Employee employee){
        if (employee == null){
            throw new IllegalArgumentException("Передали пустое значение");
        }

        return employeeRepository.save(employee);
    }

    public List<EmployeeProjection> getAllEmployees() {
        return employeeRepository.findAllProjectedBy();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Работник не найден"));
    }

    public List<EmployeeProjection> getEmployeeProjectionByDepartment(Long depart_id){
        return employeeRepository.findByDepartmentIdWithProjection(depart_id);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }
}
