package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.Notification;

import com.example.login.LoginExample.Models.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotifiRepository extends JpaRepository<Notification, Long> {
    @PersistenceContext

    @Query(value = "SELECT *from notifications WHERE user_id=?1",nativeQuery = true)
    List<Notification> findNotification(long user_id);


    @Query(value = "SELECT new com.example.login.LoginExample.Models.Notify(j.job, n.notification) from notifications n INNER JOIN n.jobs j WHERE user_id=?1",nativeQuery = true)
    List<Notify> findNotify(long user_id);




    /*@Query("SELECT new com.example.login.LoginExample.Models.Notify(j.job, n.notification) "
            + "FROM jobs j, notifications n")
    List<Notify> fetchEmpDeptDataCrossJoin();*/
}
