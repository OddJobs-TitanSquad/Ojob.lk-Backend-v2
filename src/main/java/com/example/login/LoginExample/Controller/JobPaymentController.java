package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobApplication;
import com.example.login.LoginExample.Models.JobPayment;
import com.example.login.LoginExample.Models.JobPost;
import com.example.login.LoginExample.Repository.JobApplicationRepository;
import com.example.login.LoginExample.Repository.JobPaymentRepository;
import com.example.login.LoginExample.Repository.JobPostRepository;
import com.example.login.LoginExample.Repository.PaymentPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/post-jobs/payments")
public class JobPaymentController {
    @Autowired
    private final JobPaymentRepository jobPaymentRepository;
    @Autowired
    private final JobPostRepository jobPostRepository;
    @Autowired
    private final PaymentPackageRepository paymentPackageRepository;

    @Autowired
    private final JobApplicationRepository jobApplicationRepository;

    public JobPaymentController(JobPaymentRepository jobPaymentRepository, JobPostRepository jobPostRepository, PaymentPackageRepository paymentPackageRepository, JobApplicationRepository jobApplicationRepository) {
        this.jobPaymentRepository = jobPaymentRepository;
        this.jobPostRepository = jobPostRepository;
        this.paymentPackageRepository=paymentPackageRepository;
        this.jobApplicationRepository=jobApplicationRepository;

    }

    @RequestMapping(method = RequestMethod.POST)
    public JobPayment makePayment(@RequestBody JobPayment jobPayment) {
        jobPayment.setDateTime(this.getTimeStamp());
        JobPostController jobPostController=new JobPostController(this.jobPostRepository, this.jobApplicationRepository);
        JobPost paidJobPost= jobPostController.getPostedJobsByJobId(jobPayment.getJobId());
        Date newExpireDate = extendDays(paidJobPost.getExpireDate(),getPackegeDuration(jobPayment.getPackageId()));
        paidJobPost.setExpireDate(newExpireDate);

        jobPostController.updateJobPost(paidJobPost,paidJobPost.getJobId());
        return jobPaymentRepository.save(jobPayment);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobPayment> getAllJobPostPayments() {
        return jobPaymentRepository.findAll();
    }

    public Date getTimeStamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public Date extendDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH , days);
        return cal.getTime();
    }
    public int getPackegeDuration(int packageId){
        PaymentPackageController paymentPackageController = new PaymentPackageController(this.paymentPackageRepository,this.jobPaymentRepository);
        return paymentPackageController.getPackagesById(packageId).getPackageDuration();
    }
}
