package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<ScheduleEntity> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<ScheduleEntity> findByPetId(Long petId) {
        return scheduleRepository.findByPets(petRepository.getOne(petId));
    }

    @Override
    public List<ScheduleEntity> findByCustomerId(Long customerId) {
        List<ScheduleEntity> result = new ArrayList<>();
        List<PetEntity> pets = petRepository.findByOwner(customerId);
        for (PetEntity pet : pets) {
            result.addAll(scheduleRepository.findByPets(pet));
        }
        return result;
    }

    @Override
    public List<ScheduleEntity> findByEmployeeId(Long employeeId) {
        return scheduleRepository.findByEmployees(employeeRepository.getOne(employeeId));
    }

    @Override
    public ScheduleEntity create(ScheduleDTO schedule) {
        ScheduleEntity entity = new ScheduleEntity();
        List<PetEntity> listPet = petRepository.findAllById(schedule.getPetIds());
        List<EmployeeEntity> listEmployee = employeeRepository.findByIdIn(schedule.getEmployeeIds());
        entity.setDate(schedule.getDate());
        entity.setEmployees(listEmployee);
        entity.setPets(listPet);
        entity.setActivities(schedule.getActivities());
        return scheduleRepository.save(entity);
    }
}
