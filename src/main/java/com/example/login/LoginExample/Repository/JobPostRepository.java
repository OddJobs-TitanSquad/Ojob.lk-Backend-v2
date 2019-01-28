package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface JobPostRepository extends JpaRepository<JobPost,Long> {
    JobPost findByJobId(long id);
    @Query("SELECT p FROM JobPost p WHERE p.providerId = :id")
    List<JobPost> findByProviderId(@Param("id") long id);

    @Query("SELECT DISTINCT p.city FROM JobPost p")
    List<String> getAllCities();

    @Query("SELECT p FROM JobPost p WHERE p.city=:city")
    List<JobPost> getJobByCity(@Param("city") String city);

    @Query("SELECT p FROM JobPost p WHERE p.jobType=:jobtype")
    List<JobPost> getJobByType(@Param("jobtype") String jobtype);

    @Query("SELECT p FROM  JobPost p WHERE p.isPublish=?1 and current_timestamp >= p.publishOnDate and current_timestamp < p.expireDate")
    List<JobPost> findAllValidJobs(boolean boo);
}
