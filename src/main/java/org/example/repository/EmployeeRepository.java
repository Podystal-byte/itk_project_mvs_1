package org.example.repository;

import org.example.model.Employee;
import org.example.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e")
    List<EmployeeProjection> findAllProjectedBy();

    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<EmployeeProjection> findByDepartmentIdWithProjection(@Param("departmentId") Long departmentId);
}
