package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.ICustomerService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity findByPetId(Long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    @Override
    public CustomerEntity create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDTO.getName());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        customerEntity.setNotes(customerDTO.getNotes());
        List<PetEntity> listPet = new ArrayList<>();
        List<Long> listPetId = customerDTO.getPetIds();
        if (!CollectionUtils.isEmpty(listPetId)) {
            listPetId.forEach(e -> {
                Optional<PetEntity> petEntity = petRepository.findById(e);
                petEntity.ifPresent(listPet::add);
            });
        }
        customerEntity.setPets(listPet);
        return customerRepository.save(customerEntity);
    }
}
