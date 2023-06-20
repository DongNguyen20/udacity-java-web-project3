package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements IPetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public PetEntity findById(Long petId) {
        return petRepository.getOne(petId);
    }

    @Override
    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    @Override
    public List<PetEntity> findByOwnerId(Long ownerId) {
        return petRepository.findByOwner(ownerId);
    }

    @Override
    public PetEntity create(PetDTO pet) {
        PetEntity entity = new PetEntity();
        CustomerEntity customer = customerRepository.getOne(pet.getOwnerId());
        entity.setName(pet.getName());
        entity.setBirthDate(pet.getBirthDate());
        entity.setType(pet.getType());
        entity.setNotes(pet.getNotes());
        entity.setCustomer(customer);
        entity = petRepository.save(entity);
        customer.getPets().add(entity);
        customerRepository.save(customer);
        return entity;
    }
}
