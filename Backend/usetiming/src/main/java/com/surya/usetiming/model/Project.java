package com.surya.usetiming.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Project")
public class Project extends BaseModel {
    private String title;
    private String description;

    @ManyToMany(mappedBy = "projects")
    private List<User> user;
}
