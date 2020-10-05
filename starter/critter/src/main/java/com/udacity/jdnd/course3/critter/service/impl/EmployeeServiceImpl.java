package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee saveEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployeeById(long id) {
        return null;
    }

    @Override
    public List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        return null;
    }
}
