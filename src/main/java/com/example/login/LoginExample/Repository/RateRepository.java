package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query(value = "SELECT Count(r) from rate r WHERE r.personal_id=?1 ",nativeQuery = true)
    long fiveStars(long personal_id);

    @Query(value = "SELECT Count(r) from rate r WHERE r.personal_id=?1 && r.rate=5 ",nativeQuery = true)
    long CountFiveStar(long personal_id);

}
