package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.exception.ObjectNotFoundException;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;
    CustomerRepository customerRepository;
    EmployeeRepository employeeRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getSchedulesForPet(long petId) {
        return scheduleRepository.findAllByPetsId(petId);
    }

    @Override
    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    @Override
    public List<Schedule> getScheduleForCustomer(long customerId) {

        String errorMessage = "Customer not found in Id : " + customerId;

        Optional<Customer> optionalCustomer= customerRepository.findById(customerId);
        Customer customer = (Customer) optionalCustomer.orElseThrow(() -> new ObjectNotFoundException(errorMessage));
        List<Pet> pets = customer.getPets();

        ArrayList<Schedule> schedules = new ArrayList<>();
        for (Pet pet : pets) {
            schedules.addAll(scheduleRepository.findAllByPetsId(pet.getId()));
        }
        return schedules;
    }
}
