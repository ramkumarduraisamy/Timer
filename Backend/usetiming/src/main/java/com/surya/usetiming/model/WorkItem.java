package com.surya.usetiming.model;

import com.azure.core.annotation.Get;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "work_item")
public class WorkItem extends BaseModel{

    @Column(name = "issue_id", length = 10)
    private String issueId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User assignee;

    @OneToMany(mappedBy = "workItem")
    private List<WorkItemTimeSheet> workItemTimeSheets;
}
