package com.example.vaadindemo.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = Room.FIND_ALL, query = "select n from Room n")
@Entity
@Table
public class Room implements Serializable {

    public static final String FIND_ALL = "Room.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer level;

    public Room(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
