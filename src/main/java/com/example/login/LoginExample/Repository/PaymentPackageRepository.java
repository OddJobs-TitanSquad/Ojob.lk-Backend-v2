package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.PaymentPackage;
import com.example.login.LoginExample.Models.PaymentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentPackageRepository extends JpaRepository<PaymentPackage,Integer> {
    PaymentPackage findByPackageId(int id);
    PaymentPackage findByPackageDuration(int packageDuration);

}
