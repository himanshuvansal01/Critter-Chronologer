package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRespository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServicelayer {

    @Autowired
    EmployeeRespository employeeRespository;

    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity){
        EmployeeEntity newEmployeeEntity;
        newEmployeeEntity = employeeRespository.save(employeeEntity);
        return newEmployeeEntity;
    }

    public EmployeeEntity getEmployeeId(Long employeeId){
        EmployeeEntity employeeEntity;
        employeeEntity = employeeRespository.getOne(employeeId);
        return  employeeEntity;
    }

    public void setAvailability(Set<DayOfWeek> days, long employeeId){
        EmployeeEntity employeeEntity = employeeRespository.getOne(employeeId);
        employeeEntity.setAvailableDays(days);
        employeeRespository.save(employeeEntity);

    }

    public List<EmployeeEntity> getEmployeeService(LocalDate date, Set<EmployeeSkill> Employeeskills){
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeRespository.findByDaysAvailability(date.getDayOfWeek())) {
            if (employeeEntity.getSkills().containsAll(Employeeskills)) {
                employeeEntities.add(employeeEntity);
            }
        }

        return employeeEntities;

    }
}
