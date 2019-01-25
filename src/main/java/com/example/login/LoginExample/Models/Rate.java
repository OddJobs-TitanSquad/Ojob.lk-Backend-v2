package com.example.login.LoginExample.Models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "Rate")
public class Rate {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int rate;

    public Rate(int rate, long userId, long jobId, long personalId) {
        this.rate = rate;
        this.userId = userId;
        this.jobId = jobId;
        this.personalId=personalId;
    }

    private long userId;
    private long jobId;

    public long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(long personalId) {
        this.personalId = personalId;
    }

    private long personalId;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }




        public Rate() {

        }



}
