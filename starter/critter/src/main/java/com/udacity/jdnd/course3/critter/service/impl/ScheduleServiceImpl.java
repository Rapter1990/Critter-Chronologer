package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return null;
    }

    @Override
    public List<Schedule> getAll() {
        return null;
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
