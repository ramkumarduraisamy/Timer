package com.surya.usetiming.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "log_type_lookup")
public class LogType extends BaseModel{
    private String type;
    private String description;
}
