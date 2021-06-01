package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRespository extends JpaRepository<EmployeeEntity,Long> {
    List<EmployeeEntity> findByDaysAvailability(DayOfWeek dayOfWeek);
}
