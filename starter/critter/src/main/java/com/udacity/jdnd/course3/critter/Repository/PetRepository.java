package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity,Long> {
     List<PetEntity> findPetEntityByCustomerEntityId(Long customerEntityId);


}
