package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

import java.util.List;

public interface IScheduleService {
    List<ScheduleEntity> findAll();
    List<ScheduleEntity> findByPetId(Long petId);
    List<ScheduleEntity> findByCustomerId(Long customerId);
    List<ScheduleEntity> findByEmployeeId(Long employeeId);
    ScheduleEntity create(ScheduleDTO schedule);
}
