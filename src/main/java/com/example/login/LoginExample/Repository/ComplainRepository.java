package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplainRepository extends JpaRepository<Complain,Long> {

   /* @Query("SELECT c FROM Complain c WHERE c.complainer= :id AND c.adminReply =:adminReply ")
    List<Complain> complainReplies(@Param("id") long id,@Param("adminReply") String adminReply );*/
}
