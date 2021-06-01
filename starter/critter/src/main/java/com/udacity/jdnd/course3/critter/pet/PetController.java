package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import com.udacity.jdnd.course3.critter.Service.PetServicelayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetServicelayer petServicelayer;

    public PetDTO convertToPetDTO(PetEntity petEntity){
        return new PetDTO(petEntity.getId(),petEntity.getType(),petEntity.getName(),petEntity.getCustomerEntity().getId(),petEntity.getBirtDate(),petEntity.getNotes());
    }


    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        PetEntity petEntity = new PetEntity(petDTO.getType(),petDTO.getName(),petDTO.getBirthDate(),petDTO.getNotes());
        PetDTO convertPet;

        try{
            convertPet = convertToPetDTO(petServicelayer.savePets(petEntity,petDTO.getOwnerId()));
        }catch(Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet could not be saved", exception);

        }
        return convertPet;


    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        PetEntity petEntity;

        try{

            petEntity = petServicelayer.getPetId(petId);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR: Pet with id: " + petId + " not found ", exception);
        }

        return convertToPetDTO(petEntity);
    }

    @GetMapping
    public List<PetDTO> getPets(){

        List<PetEntity> petEntities = petServicelayer.getAllPets();
        return petEntities.stream().map(this::convertToPetDTO).collect(Collectors.toList());

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetEntity> petEntities;

        try{
            petEntities = petServicelayer.getPetsbyCustomerID(ownerId);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR: Owner pet with id" + ownerId + " not found", exception);
        }

        return petEntities.stream().map(this::convertToPetDTO).collect(Collectors.toList());
    }
}
