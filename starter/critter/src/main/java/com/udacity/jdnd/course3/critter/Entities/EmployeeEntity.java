package com.udacity.jdnd.course3.critter.Entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String Name;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailability;

    public EmployeeEntity(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailability) {
        this.id = id;
        this.Name = name;
        this.skills = skills;
        this.daysAvailability = daysAvailability;
    }

    public EmployeeEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getAvailableDays() {
        return daysAvailability;
    }

    public void setAvailableDays(Set<DayOfWeek> availableDays) {
        this.daysAvailability = availableDays;
    }
}
