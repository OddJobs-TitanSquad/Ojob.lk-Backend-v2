package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.PaymentPackage;
import com.example.login.LoginExample.Repository.PaymentPackageRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/post-jobs/payment-packages")
public class PaymentPackageController {

    private final PaymentPackageRepository paymentPackageRepository;
    public PaymentPackageController(PaymentPackageRepository paymentPackageRepository){
        this.paymentPackageRepository=paymentPackageRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public PaymentPackage create(@RequestBody PaymentPackage paymentPackage) {
        return paymentPackageRepository.save(paymentPackage);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   // @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    PaymentPackage getPackagesById(@PathVariable int id) {
         return paymentPackageRepository.findByPackageId(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
   // @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    Iterable<PaymentPackage> getAllPaymentPackages() {
        return paymentPackageRepository.findAll();
    }
}
