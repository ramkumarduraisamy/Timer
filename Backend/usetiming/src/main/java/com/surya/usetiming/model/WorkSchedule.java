package com.surya.usetiming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "work_schedules_lookup")
public class WorkSchedule extends BaseModel{
    private String title;

    @Column(nullable = false)
    private String description;
}
