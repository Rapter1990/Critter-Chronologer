package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PetServiceImpl implements PetService {

    @Override
    public Pet savePet(Pet pet) {
        return null;
    }

    @Override
    public Pet getPetById(long id) {
        return null;
    }

    @Override
    public List<Pet> getAllPets() {
        return null;
    }

    @Override
    public List<Pet> getAllByOwnerId(long ownerId) {
        return null;
    }
}
