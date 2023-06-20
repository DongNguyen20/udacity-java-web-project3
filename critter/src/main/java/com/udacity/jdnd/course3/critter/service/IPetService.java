package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;

import java.util.List;

public interface IPetService {
    PetEntity findById(Long petId);
    List<PetEntity> findAll();
    List<PetEntity> findByOwnerId(Long ownerId);
    PetEntity create(PetDTO pet);
}
