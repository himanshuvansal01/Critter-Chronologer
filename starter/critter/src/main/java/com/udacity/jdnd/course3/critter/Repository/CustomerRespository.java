package com.udacity.jdnd.course3.critter.Repository;

import com.udacity.jdnd.course3.critter.Entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRespository extends JpaRepository<CustomerEntity, Long>  {
}
