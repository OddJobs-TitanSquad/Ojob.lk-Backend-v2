package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.NotifyProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotifyProviderRepository extends JpaRepository<NotifyProvider,Long> {
    @Query("SELECT c FROM NotifyProvider c WHERE c.userId= :id")
    List<NotifyProvider> NotifyProvider(@Param("id") long id);
}
