package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import com.udacity.jdnd.course3.critter.Entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRespository  extends JpaRepository<ScheduleEntity,Long> {

    List<ScheduleEntity> findByPetEntities(PetEntity petEntities);
    List<ScheduleEntity> findByEmployeeEntities(EmployeeEntity employeeEntities);
    List<ScheduleEntity> findByPetEntitiesIn(List<PetEntity> petEntitiesList);
}
