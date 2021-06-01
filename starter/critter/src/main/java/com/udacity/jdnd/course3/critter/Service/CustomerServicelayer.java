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
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServicelayer {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRespository customerRespository;

    public CustomerEntity saveCustomers(CustomerEntity customerEntity, List<Long> petIds){
        List<PetEntity> pets = new ArrayList<>();
        if(petIds != null && !petIds.isEmpty()){
            List<PetEntity> list = new ArrayList<>();
            for (Long petId : petIds) {
                PetEntity one =  petRepository.getOne(petId);
                list.add( one);
            }
            pets = list;


        }
        customerEntity.setPets(pets);
        return customerRespository.save(customerEntity);

    }

    public List<CustomerEntity> getAllCustomers(){
        List<CustomerEntity> customerEntities;
        customerEntities = customerRespository.findAll();
        return customerEntities;
    }
    
    public CustomerEntity getCustomerEntityId(long petId){
        
        CustomerEntity customerEntity;
        customerEntity = petRepository.getOne(petId).getCustomerEntity();
        return customerEntity;
        
    }
    
    
}
