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
public class JobPost {
    @Id
    @GeneratedValue
    private Long jobId;
    private @NonNull
    String title;
    private String description;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private Boolean isPublish;
    private Integer latitude;
    private Integer longitude;
    private Date postedDateTime;
    private Date expireDate;
    private String jobType;
    private Long providerId;

}
