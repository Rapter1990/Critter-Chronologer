package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="CUSTOMER",catalog ="critter")
public class Customer extends User implements Serializable {

    @Column(name="PHONE_NUMBER", length = 255)
    private String phoneNumber;

    @Column(name="NOTES", length = 512)
    private String notes;

    public Customer() {

    }

    public Customer(long id, String name, String phoneNumber, String notes) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
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
}
