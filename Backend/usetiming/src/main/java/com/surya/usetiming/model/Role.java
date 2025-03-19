package com.surya.usetiming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles_lookup")
@Getter
@Setter
public class Role extends BaseModel{
    @Column(nullable = false)
    private String title;

    private String description;

}
