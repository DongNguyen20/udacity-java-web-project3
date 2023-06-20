package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    public static CustomerDTO convertToCustomerDTO(CustomerEntity entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setNotes(entity.getNotes());
        List<Long> petIds = entity.getPets().stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList());
        dto.setPetIds(petIds);
        return dto;
    }

    public static EmployeeDTO convertToEmployeeDTO(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setName(employeeEntity.getName());
        employeeDTO.setSkills(employeeEntity.getSkills());
        employeeDTO.setDaysAvailable(employeeEntity.getDaysAvailable());
        return employeeDTO;
    }

    public static PetDTO convertToPetDTO(PetEntity petEntity) {
        PetDTO dto = new PetDTO();
        dto.setId(petEntity.getId());
        dto.setType(petEntity.getType());
        dto.setName(petEntity.getName());
        dto.setOwnerId(petEntity.getCustomer().getId());
        dto.setBirthDate(petEntity.getBirthDate());
        dto.setNotes(petEntity.getNotes());
        return dto;
    }

    public static ScheduleDTO convertToScheduleDTO(ScheduleEntity entity) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        List<Long> employeeIds = entity.getEmployees().stream()
                .map(EmployeeEntity::getId)
                .collect(Collectors.toList());
        dto.setEmployeeIds(employeeIds);
        List<Long> petIds = entity.getPets().stream()
                .map(PetEntity::getId)
                .collect(Collectors.toList());
        dto.setPetIds(petIds);
        dto.setActivities(entity.getActivities());
        return dto;
    }
}
