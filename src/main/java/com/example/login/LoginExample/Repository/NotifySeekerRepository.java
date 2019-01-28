package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.NotifySeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotifySeekerRepository extends JpaRepository<NotifySeeker,Long> {
    @Query("SELECT c FROM NotifySeeker c WHERE c.userId= :id")
    List<NotifySeeker> NotifySeeker(@Param("id") long id);
}
