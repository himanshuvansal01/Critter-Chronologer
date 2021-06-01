package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import com.udacity.jdnd.course3.critter.Entities.ScheduleEntity;
import com.udacity.jdnd.course3.critter.Service.ScheduleServicelayer;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleServicelayer scheduleServicelayer;

    public ScheduleDTO convertScheduleToScheduleDTO(ScheduleEntity scheduleEntity){
        List<Long> employeeId = scheduleEntity.getEmployeeEntities().stream().map(EmployeeEntity::getId).collect(Collectors.toList());
        List<Long> petId = scheduleEntity.getPetEntities().stream().map(PetEntity::getId).collect(Collectors.toList());

        return new ScheduleDTO(scheduleEntity.getId(),employeeId,petId,scheduleEntity.getDate(),scheduleEntity.getActivities());

    }
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity scheduleEntity = new ScheduleEntity(scheduleDTO.getDate(),  scheduleDTO.getActivities());
        ScheduleDTO convertSchedule;
        try{
            convertSchedule = convertScheduleToScheduleDTO(scheduleServicelayer.saveSchedule(scheduleEntity,scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds()));
        }catch(Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ERROR: schedule could not be saved",exception);

        }

        return convertSchedule;


    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleEntity> scheduleEntities = scheduleServicelayer.getSchedules();
        return scheduleEntities.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());



    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<ScheduleEntity> scheduleEntities;

        try{
            scheduleEntities = scheduleServicelayer.getPetSchedule(petId);

        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ERROR: Pet schedule with id:" + petId + "not found",exception);
        }
        return scheduleEntities.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

        List<ScheduleEntity> scheduleEntities;
        try{
            scheduleEntities = scheduleServicelayer.getEmployeeSchedule(employeeId);

        }catch(Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ERROR: Employee schedule with employee id:" + employeeId + " not found ", exception);
        }
        return scheduleEntities.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());

    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleEntity> scheduleEntities;
        try{
            scheduleEntities = scheduleServicelayer.getCustomerSchedule(customerId);

        }catch(Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ERROR: Employee schedule with employee id:" + customerId + " not found ", exception);
        }
        return scheduleEntities.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());

    }
}
