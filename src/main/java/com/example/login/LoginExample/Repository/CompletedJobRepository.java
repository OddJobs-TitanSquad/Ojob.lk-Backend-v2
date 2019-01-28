package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.CompletedJob;
import com.example.login.LoginExample.Models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompletedJobRepository extends JpaRepository<CompletedJob,Long> {
    @Query("SELECT c FROM CompletedJob c WHERE c.jobApplicationId IN(Select j FROM JobApplication j WHERE j.userId = :id)")
    List<CompletedJob> findBySeekerId(@Param("id") long id);

    @Query("SELECT c FROM CompletedJob c WHERE c.employerId= :id")
    List<CompletedJob> findByProviderId(@Param("id") long id);

    @Query("SELECT COUNT(c) FROM CompletedJob c WHERE c.jobApplicationId IN(Select j FROM JobApplication j WHERE j.userId = :id)")
    int CountBySeeker(@Param("id") long id);

    @Query("SELECT COUNT(c) FROM CompletedJob c WHERE c.employerId= :id")
    int CountByProvider(@Param("id") long id);
}
