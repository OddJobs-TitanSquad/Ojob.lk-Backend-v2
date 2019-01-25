package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobRepository  extends JpaRepository<Job, Long>{
    @Query(value = "SELECT *from jobs WHERE sid=?1",nativeQuery = true)
    List<Job> findUserCompleted(long sid);

    @Query(value = "SELECT *from jobs WHERE pid=?1",nativeQuery = true)
    List<Job> findUserProvided(long pid);

    @Override
    Optional<Job> findById(Long aLong);




}
