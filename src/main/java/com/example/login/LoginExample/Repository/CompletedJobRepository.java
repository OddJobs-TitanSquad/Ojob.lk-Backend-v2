package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.CompletedJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedJobRepository extends JpaRepository<CompletedJob,Long> {

}
