package com.surya.usetiming.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "work_item_timesheet")
public class WorkItemTimeSheet extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "work_item_id")
    private WorkItem workItem;

    @ManyToOne
    @JoinColumn
    private Timesheet timesheet;
}
