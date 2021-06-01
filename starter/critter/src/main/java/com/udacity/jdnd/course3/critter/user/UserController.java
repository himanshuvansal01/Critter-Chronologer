package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Entities.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entities.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entities.PetEntity;
import com.udacity.jdnd.course3.critter.Service.CustomerServicelayer;
import com.udacity.jdnd.course3.critter.Service.EmployeeServicelayer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerServicelayer customerServicelayer;

    @Autowired
    EmployeeServicelayer employeeServicelayer;

    private EmployeeDTO convertEmployeeToEmployeeDTO(EmployeeEntity employeeEntity){
        return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getSkills(),employeeEntity.getAvailableDays());
    }

    private CustomerDTO convertCustomerToCustomerDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO = new CustomerDTO();

        BeanUtils.copyProperties(customerEntity,customerDTO,"pets");


        List<Long> petId = new ArrayList<>();
        for (PetEntity petEntity : customerEntity.getPets()) {
            Long id = petEntity.getId();
            petId.add(id);
        }

       customerDTO.setPetIds(petId);
        return customerDTO;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity(customerDTO.getId(),customerDTO.getName(),customerDTO.getPhoneNumber(),customerDTO.getNotes());
        List<Long> petIds = customerDTO.getPetIds();

        CustomerDTO covertCustomer;
        try{
            covertCustomer = convertCustomerToCustomerDTO(customerServicelayer.saveCustomers(customerEntity,petIds));
        }catch(Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR: Customer could not be saved", exception);

        }
        return covertCustomer;


    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerEntity> customerEntities = customerServicelayer.getAllCustomers();
        List<CustomerDTO> list = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerDTO customerDTO = convertCustomerToCustomerDTO(customerEntity);
            list.add(customerDTO);
        }
        return list;


    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        CustomerEntity customerEntity;

        try{
            customerEntity = customerServicelayer.getCustomerEntityId(petId);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ERROR: Owner pet with id:" + petId + " not found ",exception);

        }

        return convertCustomerToCustomerDTO(customerEntity);


    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = new EmployeeEntity(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getSkills(),employeeDTO.getDaysAvailable());
        EmployeeDTO convertEmployee;

        try{
            convertEmployee = convertEmployeeToEmployeeDTO(employeeServicelayer.saveEmployee(employeeEntity));
        }catch (Exception exception){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," ERROR: Employee could not be saved", exception);

        }

        return convertEmployee;

    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        EmployeeEntity employeeEntity;
        try{
            employeeEntity = employeeServicelayer.getEmployeeId(employeeId);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ERROR: Employee with id: " + employeeId + " not found ", exception);
        }
        return convertEmployeeToEmployeeDTO(employeeEntity);

    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        try{
            employeeServicelayer.setAvailability(daysAvailable,employeeId);
        }catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " ERROR: Employee with id " + employeeId + " not found ", exception );

        }

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeEntity> employeeEntities = employeeServicelayer
                .getEmployeeService(employeeDTO.getDate(),employeeDTO.getSkills());
        return employeeEntities.stream().map(this::convertEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

}
