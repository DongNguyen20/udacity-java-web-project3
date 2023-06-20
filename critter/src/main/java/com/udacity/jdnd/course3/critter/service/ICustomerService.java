package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    CustomerEntity findById(Long id);
    List<CustomerEntity> findAll();
    CustomerEntity findByPetId(Long petId);
    CustomerEntity create(CustomerDTO customerDTO);
}
