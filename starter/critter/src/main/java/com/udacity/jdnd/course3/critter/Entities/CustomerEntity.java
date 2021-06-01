package com.udacity.jdnd.course3.critter.Entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;


@Table
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String Name;
    private String phoneNumber;
    private String Notes;

    @OneToMany(targetEntity = PetEntity.class)
    private List<PetEntity> pets;

    public CustomerEntity(Long id, String name, String phoneNumber, String notes) {
        this.id = id;
        this.Name = name;
        this.phoneNumber = phoneNumber;
        this.Notes = notes;
    }

    public CustomerEntity() {
    }


    public List<PetEntity> getPets() {
        return pets;
    }

    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
