package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface JobPostRepository extends JpaRepository<JobPost,Long> {
    JobPost findByJobId(long id);
    @Query("SELECT p FROM JobPost p WHERE p.providerId = :id")
    List<JobPost> findByProviderId(@Param("id") long id);
}
