package com.udacity.jdnd.course3.critter.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public abstract class User implements Serializable {

    @Id
    @SequenceGenerator(name="USER_SEQ", sequenceName="USER_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="USER_SEQ")
    @Column(name="ID", nullable = false,unique = true)
    private long id;

    @Nationalized
    @Column(name="NAME", nullable = false, length = 255)
    private String name;

    public User() {

    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
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
}
