package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    @Query("SELECT p FROM JobApplication p WHERE p.jobId IN(Select j FROM JobPost j WHERE j.providerId = :id)")
    List<JobApplication> findByUserId(@Param("id") long id);


}
