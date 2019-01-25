package com.example.login.LoginExample.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // Says that this is the Entity
@Getter // Automatically getter and setter method using Lombok
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class JobType {
    @Id
    @GeneratedValue
    private Long jobTypeId;
    private @NonNull
    String jobType;
}
