package com.surya.usetiming.model;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "project")
    private List<ProjectUser> projectUsers;
}
