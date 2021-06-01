package com.udacity.jdnd.course3.critter.Entities;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;


@Table
@Entity
public class PetEntity {

    @Id
    @GeneratedValue
    private Long id;

    private PetType Type;

    @Nationalized
    private String Name;
    @ManyToOne(targetEntity = CustomerEntity.class,optional = false )
    private CustomerEntity customerEntity;
    private LocalDate BirtDate;
    private String  Notes;

    public PetEntity(PetType type, String name, LocalDate birtDate, String notes) {
        this.Type = type;
        this.Name = name;
        this.BirtDate = birtDate;
        this.Notes = notes;
    }

    public PetEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }


    public PetType getType() {
        return Type;
    }

    public void setType(PetType type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LocalDate getBirtDate() {
        return BirtDate;
    }

    public void setBirtDate(LocalDate birtDate) {
        BirtDate = birtDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
