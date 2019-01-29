package com.example.login.LoginExample.Repository;
import com.example.login.LoginExample.Models.JobPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPaymentRepository extends JpaRepository<JobPayment,Long> {
   JobPayment findByPackageId(int packageId);
}
