package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.service.impl.CustomerServiceImpl;
import com.udacity.jdnd.course3.critter.service.impl.PetServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    PetServiceImpl petService;
    CustomerServiceImpl customerService;

    @Autowired
    public PetController(PetServiceImpl petService, CustomerServiceImpl customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        String petName = petDTO.getName();
        PetType petType = petDTO.getType();
        Long ownerId = petDTO.getOwnerId();
        LocalDate birthDate = petDTO.getBirthDate();
        String notes = petDTO.getNotes();
        long petDTOId = petDTO.getId();

        Customer customer = customerService.findById(ownerId);

        Pet pet = new Pet();
        pet.setName(petName);
        pet.setPetType(petType);
        pet.setOwner(customer);
        pet.setBirthDate(birthDate);
        pet.setNotes(notes);

        if (petDTOId != 0) {
            pet = petService.getPetById(petDTOId);
        }

        Pet saved = petService.savePet(pet);

        customer.getPets().add(saved);
        customerService.saveCustomer(customer);

        petDTO.setId(saved.getId());
        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        Pet pet = petService.getPetById(petId);

        PetDTO petDTO = getPetDTO(pet);

        return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAllPets();
        return pets.stream().map(this::getPetDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> pets = petService.getAllByOwnerId(ownerId);

        return pets.stream().map(this::getPetDTO).collect(Collectors.toList());
    }


    private PetDTO getPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setName(pet.getName());
        petDTO.setNotes(pet.getNotes());
        petDTO.setOwnerId(pet.getOwner().getId());
        petDTO.setType(pet.getPetType());
        return petDTO;
    }
}
