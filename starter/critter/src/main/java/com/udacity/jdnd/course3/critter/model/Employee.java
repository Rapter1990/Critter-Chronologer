package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.List;

@Entity
@Table(name="EMPLOYEE",catalog ="critter")
public class Employee extends User implements Serializable {

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> daysAvailable;

    public Employee() {

    }

    public Employee(long id, String name, List<EmployeeSkill> skills, List<DayOfWeek> daysAvailable) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public List<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public List<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(List<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
