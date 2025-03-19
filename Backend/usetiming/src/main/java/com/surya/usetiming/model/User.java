package com.surya.usetiming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseModel {
    @Column(nullable = false, name = "name")
    private String displayName;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private WorkSchedule schedule;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkItem> workItem;

    @ManyToMany
    @JoinTable(
            name = "project_user"
            , joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;
}
