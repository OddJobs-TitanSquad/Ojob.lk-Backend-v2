package com.example.login.LoginExample.Repository;

import com.example.login.LoginExample.Models.PaymentPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPackageRepository extends JpaRepository<PaymentPackage,Integer> {
    PaymentPackage findByPackageId(int id);
}
