package com.example.login.LoginExample.Models;

import lombok.*;

import javax.persistence.*;

@Entity // Says that this is the Entity

@Getter // Automatically getter and setter method using Lombok
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table
public class PaymentPackage {
    @Id
    @GeneratedValue
    private int packageId;
    private String packageName;
    @Column(unique=true)
    private int packageDuration;
    private int price;
}
