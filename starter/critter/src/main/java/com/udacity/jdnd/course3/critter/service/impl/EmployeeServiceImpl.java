package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.exception.ObjectNotFoundException;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {

        Employee employee = new Employee();

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if(optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }else {
            throw new ObjectNotFoundException("Employee not found By Id : " + employeeId);
        }

        return employee;
    }

    @Override
    public List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {

        List<Employee> employees = employeeRepository.findAllByDaysAvailableContaining(dayOfWeek);
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee e : employees){
            if(e.getSkills().containsAll(skills)) {
                availableEmployees.add(e);
            }
        }
        return availableEmployees;
    }
}
