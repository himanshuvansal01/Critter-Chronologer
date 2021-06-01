package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import com.udacity.jdnd.course3.critter.Repository.CustomerRespository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetServicelayer {

    @Autowired
    CustomerRespository customerRespository;

    @Autowired
    PetRepository petRepository;

    public List<PetEntity> getPetsbyCustomerID(long customerentityid){
        List<PetEntity> petEntities = petRepository.findPetEntityByCustomerEntityId(customerentityid);
        return  petEntities;

    }

    public List<PetEntity> getAllPets(){
        List<PetEntity> petEntities = petRepository.findAll();
        return petEntities;
    }

    public PetEntity getPetId(Long petEntityID){
        PetEntity petEntity = petRepository.getOne(petEntityID);
        return petEntity;
    }

    public PetEntity savePets(PetEntity petEntity, Long customerID){
        CustomerEntity customerEntity = customerRespository.getOne(customerID);
        List<PetEntity> petEntities = new ArrayList<>();

        petEntity.setCustomerEntity(customerEntity);
        petEntity = petRepository.save(petEntity);
        petEntities.add(petEntity);
        customerEntity.setPets(petEntities);
        customerRespository.save(customerEntity);

        return petEntity;

    }
}
