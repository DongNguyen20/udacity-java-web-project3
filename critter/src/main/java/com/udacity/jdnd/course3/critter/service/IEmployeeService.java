package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IEmployeeService {
    EmployeeEntity findById(Long employeeId);
    List<EmployeeEntity> findByIds(List<Long> ids);
    List<EmployeeEntity> findByAvailability(Set<EmployeeSkill> skills, LocalDate date);
    EmployeeEntity create(EmployeeDTO employee);
}
