package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.IEmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity findById(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    @Override
    public List<EmployeeEntity> findByIds(List<Long> ids) {
        return employeeRepository.findByIdIn(ids);
    }

    @Override
    public List<EmployeeEntity> findByAvailability(Set<EmployeeSkill> skills, LocalDate date) {
        return employeeRepository.findByDaysAvailable(date.getDayOfWeek())
                .stream().filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
    }

    @Override
    public EmployeeEntity create(EmployeeDTO employee) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(employee.getId());
        entity.setName(employee.getName());
        entity.setSkills(employee.getSkills());
        entity.setDaysAvailable(employee.getDaysAvailable());
        return employeeRepository.save(entity);
    }
}
