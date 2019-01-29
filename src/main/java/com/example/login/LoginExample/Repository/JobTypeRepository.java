package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeRepository extends JpaRepository<JobType,Long> {
    JobType findByJobType(String jobType);

}
