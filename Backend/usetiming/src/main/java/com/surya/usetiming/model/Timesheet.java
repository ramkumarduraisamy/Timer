package com.surya.usetiming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "timesheets")
public class Timesheet extends BaseModel {
    @Column (name = "title")
    private String title;

    @Column(name = "logged_hours")
    private Double loggedHours;

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "work_description", columnDefinition = "TEXT")
    private String workDescription;

    @Column(name = "challenges", columnDefinition = "TEXT")
    private String challenges;

    @ManyToOne
    @JoinColumn(name = "log_type_id", referencedColumnName = "id")
    private LogType logType;

    @ManyToMany(mappedBy = "timesheets", cascade = CascadeType.ALL)
    private List<WorkItem> workItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;
}
