package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.PaymentPackage;
import com.example.login.LoginExample.Repository.JobPaymentRepository;
import com.example.login.LoginExample.Repository.PaymentPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
@RequestMapping("api/user/post-jobs/payment-packages")
public class PaymentPackageController {
    @Autowired
    private final PaymentPackageRepository paymentPackageRepository;

    @Autowired
    private final JobPaymentRepository jobPaymentRepository;

    public PaymentPackageController(PaymentPackageRepository paymentPackageRepository, JobPaymentRepository jobPaymentRepository){
        this.paymentPackageRepository=paymentPackageRepository;
        this.jobPaymentRepository=jobPaymentRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Boolean createPaymentPackage(@RequestBody PaymentPackage paymentPackage) {
        try{
            paymentPackageRepository.findByPackageDuration(paymentPackage.getPackageDuration()).getPackageDuration();
            return false;
        }catch (Exception e){
            paymentPackageRepository.save(paymentPackage);
            return true;
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean DeletePaymentPackage( @PathVariable int id) {
        try{this.jobPaymentRepository.findByPackageId(id).getPackageId();
            this.jobPaymentRepository.findByPackageId(id);
            return false;
        }catch (Exception e){
            this.paymentPackageRepository.deleteById(id);
            return true;
        }

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
