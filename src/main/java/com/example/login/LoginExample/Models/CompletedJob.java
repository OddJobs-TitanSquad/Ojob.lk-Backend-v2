package com.example.login.LoginExample.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity // Says that this is the Entity
@Getter // Automatically getter and setter method using Lombok
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CompletedJob {
    @Id
    @GeneratedValue
    private long completedJobId;
    private long employerId;
    private long employeeId;
    private long jobApplicationId;
    private Date completedDateTime;
}
