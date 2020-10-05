package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="PET",catalog ="critter")
public class Pet implements Serializable {

    @Id
    @SequenceGenerator(name="PET_SEQ", sequenceName="PET_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="PET_SEQ")
    @Column(name="PET_ID", nullable = false,unique = true)
    private long id;

    @Nationalized
    @Column(name="NAME", length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="PET_TYPE", length = 64)
    private PetType petType;

    @Column(name = "NOTES", length = 500)
    private String notes;

    @Column(name="BIRTH_DATE")
    private LocalDate birthDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer owner;

    public Pet() {

    }

    public Pet(long id, String name, PetType petType, String notes, LocalDate birthDate, Customer owner) {
        this.id = id;
        this.name = name;
        this.petType = petType;
        this.notes = notes;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
