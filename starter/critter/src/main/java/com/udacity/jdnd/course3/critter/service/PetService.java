package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Pet;

import java.util.List;

public interface PetService {

    Pet savePet(Pet pet, long ownerId);
    Pet getPetById(long id);
    List<Pet> getAllPets();
    List<Pet> getAllByOwnerId(long ownerId);
}
