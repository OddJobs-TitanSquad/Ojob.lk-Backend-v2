package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobApplication;
import com.example.login.LoginExample.Models.User;
import com.example.login.LoginExample.Repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RequestMapping("api/user/job-applications")
public class JobApplicationController {
    @Autowired
    private final JobApplicationRepository jobApplicationRepository;



    public JobApplicationController(JobApplicationRepository jobApplicationRepository ) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JobApplication create(@RequestBody JobApplication jobApplication) {
        jobApplication.setRequestedDateTime(getTimeStamp());
        return jobApplicationRepository.save(jobApplication);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean DeleteJobApplication( @PathVariable long id) {
        try{
            this.jobApplicationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public JobApplication acceptJobApplication(@RequestBody JobApplication jobApplication) {
       jobApplication.setIsAccepted(true);
        jobApplication.setAcceptedDate(getTimeStamp());
        jobApplicationRepository.save(jobApplication);
        return jobApplication;
    }
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public JobApplication rejectJobApplication(@RequestBody JobApplication jobApplication) {
        jobApplication.setIsRejected(true);
        jobApplication.setRejectedDate(getTimeStamp());
        jobApplicationRepository.save(jobApplication);
        return jobApplication;
    }
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public JobApplication confirmJobApplication(@RequestBody JobApplication jobApplication) {
        jobApplication.setIsConfirmed(true);
        jobApplication.setAcceptedDate(getTimeStamp());
        jobApplicationRepository.save(jobApplication);
        return jobApplication;
    }
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public JobApplication completeJobApplication(@RequestBody JobApplication jobApplication) {
        jobApplication.setIsCompleted(true);
        jobApplicationRepository.save(jobApplication);
        return jobApplication;
    }
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobApplication> getAllJobApplicationsByUserId(@PathVariable long id) {
        return jobApplicationRepository.findByUserId(id);
    }

    public Date getTimeStamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    @GetMapping("/job/jobId/{id}")
    public long findJobId(@PathVariable (value = "id") long id){
        Optional<JobApplication> optional=jobApplicationRepository.findById(id);
        JobApplication ja;
        ja= optional.get();
        return ja.getApplicationId();
    }

    @RequestMapping(value = "seeker/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobApplication> getAllJobApplicationsBySeekerId(@PathVariable long id) {
        return jobApplicationRepository.findBySeekerId(id);
    }


}
