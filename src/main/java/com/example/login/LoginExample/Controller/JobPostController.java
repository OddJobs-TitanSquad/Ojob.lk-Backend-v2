package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobApplication;
import com.example.login.LoginExample.Models.JobPost;
import com.example.login.LoginExample.Models.User;
import com.example.login.LoginExample.Repository.JobApplicationRepository;
import com.example.login.LoginExample.Repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RequestMapping("/api/user/post-jobs")
public class JobPostController {
    @Autowired
    private final JobPostRepository jobPostRepository;

    @Autowired
    private final JobApplicationRepository jobApplicationRepository;


    public JobPostController(JobPostRepository jobPostRepository, JobApplicationRepository jobApplicationRepository) {
        this.jobPostRepository = jobPostRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }


    @RequestMapping(method = RequestMethod.POST)
    public JobPost create(@RequestBody JobPost jobpost) {
        jobpost.setIsPublish(false);
        jobpost.setPostedDateTime(getTimeStamp());
        jobpost.setExpireDate(getTimeStamp());
        return jobPostRepository.save(jobpost);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean deleteJobPost( @PathVariable long id) {
        try{
            this.jobPostRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Boolean updateJobPost(@RequestBody JobPost jobPost, @PathVariable long id) {
        Optional<JobPost> jobPostOptional = jobPostRepository.findById(id);
        if (!jobPostOptional.isPresent())
            return false;
        jobPost.setJobId(id);
        jobPostRepository.save(jobPost);
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    JobPost getPostedJobsByJobId(@PathVariable long id) {
        return jobPostRepository.findByJobId(id);
    }

    @RequestMapping(value = "job-title/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String getPostedJobsTitleByJobId(@PathVariable long id) {
        JobPost jobPost = jobPostRepository.findByJobId(id);
        return jobPost.getTitle();
    }

    @RequestMapping(value = "/all/user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobPost> getAllPostedJobsByUserId(@PathVariable long id) {
        return jobPostRepository.findByProviderId(id);
    }

    public Date getTimeStamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public Boolean publishJobPost(@RequestBody JobPost jobPost) {
        if ((jobPost.getExpireDate()).compareTo(getTimeStamp()) >= 0) {
            jobPost.setPublishOnDate(getTimeStamp());
            jobPost.setIsPublish(true);
            jobPostRepository.save(jobPost);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/unpublish", method = RequestMethod.POST)
    public JobPost unpublishJobPost(@RequestBody JobPost jobPost) {
        jobPost.setIsPublish(false);

        return jobPostRepository.save(jobPost);
    }


    @GetMapping("/job/jobTitle/{id}")
    public JobPost findUserName(@PathVariable(value = "id") long id) {
        Optional<JobApplication> optional = jobApplicationRepository.findById(id);
        JobApplication ja;
        ja = optional.get();

        JobPost job = jobPostRepository.findByJobId(ja.getJobId());

        return job;

    }

 @RequestMapping(value = "/publish-on", method = RequestMethod.POST)
    public Boolean publishOnJobPost(@RequestBody JobPost jobPost) {
        if ((jobPost.getExpireDate()).compareTo(getTimeStamp()) >= 0) {
            if ((jobPost.getPublishOnDate()).compareTo(getTimeStamp()) >= 0) {
                jobPost.setExpireDate(addDiff(jobPost.getPublishOnDate(),getDiff(jobPost.getPublishOnDate(), jobPost.getExpireDate())));
                jobPost.setIsPublish(true);
                jobPostRepository.save(jobPost);
                return true;
            }else{
                jobPost.setExpireDate(addDiff(jobPost.getPublishOnDate(),getDiff(getTimeStamp(), jobPost.getExpireDate())));
                jobPost.setIsPublish(true);
                jobPostRepository.save(jobPost);
                return true;
            }

        }
        return false;
    }
    @RequestMapping(value = "/jobs/valid", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobPost> getValidJobs(){
        return jobPostRepository.findAllValidJobs(true);
    }


    public int getDiff(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        int diffmin = (int) (diff / (60 * 1000));
        return diffmin;
    }
    public Date addDiff(Date d2, int diffmin){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d2);
        calendar.add(Calendar.MINUTE, diffmin);
        return calendar.getTime();
    }

}
