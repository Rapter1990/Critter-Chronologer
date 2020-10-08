package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.service.impl.CustomerServiceImpl;
import com.udacity.jdnd.course3.critter.service.impl.EmployeeServiceImpl;
import com.udacity.jdnd.course3.critter.service.impl.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
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

    CustomerServiceImpl customerService;
    EmployeeServiceImpl employeeService;
    PetServiceImpl petService;

    @Autowired
    public UserController(CustomerServiceImpl customerService, EmployeeServiceImpl employeeService, PetServiceImpl petService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.petService = petService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        List<Long> petIds = customerDTO.getPetIds();
        if(petIds != null) {
            List<Pet> pets = petService.getAllPetsByIds(petIds);
            customer.setPets(pets);
        }

        Customer savedCustomer = customerService.saveCustomer(customer);

        customerDTO.setId(savedCustomer.getId());

        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(this::getCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.getPetById(petId);
        Customer customer = pet.getOwner();

        return getCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        String name = employeeDTO.getName();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        Set<DayOfWeek> daysAvailable = employeeDTO.getDaysAvailable();
        long id = employeeDTO.getId();

        List<EmployeeSkill> skillsList;
        List<DayOfWeek> daysAvailableList;

        if (skills != null) {
            skillsList = new ArrayList<>(skills);
        } else {
            skillsList = new ArrayList<>();
        }

        if (daysAvailable != null) {
            daysAvailableList = new ArrayList<>(daysAvailable);
        } else {
            daysAvailableList = new ArrayList<>();
        }

        employee.setName(name);
        employee.setId(id);
        employee.setDaysAvailable(daysAvailableList);
        employee.setSkills(skillsList);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        employeeDTO.setId(savedEmployee.getId());

        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        Employee employee = employeeService.getEmployeeById(employeeId);
        return getEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<DayOfWeek> daysAvailableList = new ArrayList<DayOfWeek>(daysAvailable);
        employee.setDaysAvailable(daysAvailableList);
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {

        List<Employee> employees = employeeService.getAvailableEmployees(
                employeeDTO.getSkills(), employeeDTO.getDate().getDayOfWeek());

        return employees.stream().map(this::getEmployeeDTO).collect(Collectors.toList());
    }

    public CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());

        List<Long> petIds;

        if(customer.getPets() !=null){
            petIds = customer.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        } else {
            petIds = new ArrayList<>();
        }

        customerDTO.setPetIds(petIds);

        return customerDTO;

    }

    public EmployeeDTO getEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(new HashSet<>(employee.getSkills()));
        employeeDTO.setDaysAvailable(new HashSet<>(employee.getDaysAvailable()));

        return employeeDTO;
    }

}
