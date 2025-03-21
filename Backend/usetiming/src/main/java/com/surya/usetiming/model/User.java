package com.surya.usetiming.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseModel {

    @Column(nullable = false, name = "name")
    private String displayName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private WorkSchedule schedule;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkItem> workItem;

    @OneToMany(mappedBy = "user")
    private List<ProjectUser> projectUsers;

    public User (String displayName, String email) {
        this.displayName = displayName;
        this.email = email;
    }
}
