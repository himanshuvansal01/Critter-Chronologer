package com.udacity.jdnd.course3.critter.Entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(targetEntity = EmployeeEntity.class)
    private List<EmployeeEntity> employeeEntities;

    @ManyToMany(targetEntity = PetEntity.class)
    private List<PetEntity> petEntities;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public ScheduleEntity(LocalDate date, Set<EmployeeSkill> activities) {
        this.date = date;
        this.activities = activities;
    }

    public ScheduleEntity() {
    }

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
