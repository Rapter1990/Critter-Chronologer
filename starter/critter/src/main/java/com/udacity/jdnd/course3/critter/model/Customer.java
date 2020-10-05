package com.udacity.jdnd.course3.critter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="CUSTOMER",catalog ="critter")
public class Customer extends User implements Serializable {

    @Column(name="PHONE_NUMBER", length = 255)
    private String phoneNumber;

    @Column(name="NOTES", length = 512)
    private String notes;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer() {

    }

    public Customer(long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
