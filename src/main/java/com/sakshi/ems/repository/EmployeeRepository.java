package com.sakshi.ems.repository;

import com.sakshi.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName,
            String lastName,
            String email,
            Pageable pageable
    );
    long countByStatus(String status);

    @Query("SELECT COUNT(DISTINCT e.department) FROM Employee e")
    long countDepartments();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double averageSalary();
}