package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import com.udacity.jdnd.course3.critter.Entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.Repository.CustomerRespository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRespository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleServicelayer {

    @Autowired
    ScheduleRespository scheduleRespository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRespository employeeRespository;

    @Autowired
    CustomerRespository customerRespository;

    public List<ScheduleEntity> getSchedules(){
        List<ScheduleEntity> scheduleEntities = scheduleRespository.findAll();
        return scheduleEntities;
    }

    public List<ScheduleEntity> getEmployeeSchedule(Long id){
        EmployeeEntity employeeEntity = employeeRespository.getOne(id);
        List<ScheduleEntity> scheduleEntities = scheduleRespository.findByEmployeeEntities(employeeEntity);
        return scheduleEntities;
    }

    public List<ScheduleEntity> getPetSchedule(Long id){
        PetEntity petEntity = petRepository.getOne(id);
        List<ScheduleEntity> scheduleEntities = scheduleRespository.findByPetEntities(petEntity);
        return scheduleEntities;
    }

    public List<ScheduleEntity> getCustomerSchedule(Long id){
        CustomerEntity customerEntity = customerRespository.getOne(id);
        List<ScheduleEntity> scheduleEntities = scheduleRespository.findByPetEntitiesIn(customerEntity.getPets());
        return scheduleEntities;
    }

    public ScheduleEntity saveSchedule(ScheduleEntity scheduleEntity,List<Long> employeeID, List<Long> petId){

        List<PetEntity> petEntities = petRepository.findAllById(petId);
        List<EmployeeEntity> employeeEntities = employeeRespository.findAllById(employeeID);

        scheduleEntity.setPetEntities(petEntities);
        scheduleEntity.setEmployeeEntities(employeeEntities);

        return scheduleRespository.save(scheduleEntity);


    }


}
