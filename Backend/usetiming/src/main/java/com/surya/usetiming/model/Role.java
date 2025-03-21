package com.surya.usetiming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles_lookup")
public class Role extends BaseModel{

    @Column(nullable = false)
    private String title;

    private String description;

}
