package com.sakshi.ems.service.impl;

import com.sakshi.ems.dto.employee.EmployeeDTO;
import com.sakshi.ems.entity.Employee;
import com.sakshi.ems.exception.DuplicateResourceException;
import com.sakshi.ems.exception.ResourceNotFoundException;
import com.sakshi.ems.mapper.EmployeeMapper;
import com.sakshi.ems.repository.EmployeeRepository;
import com.sakshi.ems.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new DuplicateResourceException("Employee already exists with email: "
                    + employeeDTO.getEmail());
        }

        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDTO(savedEmployee);
    }

    @Override
    public Page<EmployeeDTO> getAllEmployees(int page,
                                             int size,
                                             String sortBy,
                                             String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .findAll(pageable)
                .map(EmployeeMapper::toDTO);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        if (!employee.getEmail().equals(employeeDTO.getEmail())
                && employeeRepository.existsByEmail(employeeDTO.getEmail())) {

            throw new DuplicateResourceException(
                    "Employee already exists with email: " + employeeDTO.getEmail());
        }

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setSalary(employeeDTO.getSalary());
        employee.setJoiningDate(employeeDTO.getJoiningDate());
        employee.setStatus(employeeDTO.getStatus());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }

    @Override
    public Page<EmployeeDTO> searchEmployees(String keyword,
                                             int page,
                                             int size,
                                             String sortBy,
                                             String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword,
                        pageable
                )
                .map(EmployeeMapper::toDTO);
    }
}