package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        return null;
    }

    @Override
    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return null;
    }

    @Override
    public List<Schedule> getScheduleForCustomer(long customerId) {

        return null;
    }
}
