package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobApplication;
import com.example.login.LoginExample.Models.JobPost;
import com.example.login.LoginExample.Models.User;
import com.example.login.LoginExample.Repository.JobApplicationRepository;
import com.example.login.LoginExample.Repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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


    public JobPostController(JobPostRepository jobPostRepository,JobApplicationRepository jobApplicationRepository) {
        this.jobPostRepository = jobPostRepository;
        this.jobApplicationRepository=jobApplicationRepository;
    }


    @RequestMapping(method = RequestMethod.POST)
    public JobPost create(@RequestBody JobPost jobpost) {
        jobpost.setIsPublish(false);
        jobpost.setPostedDateTime(getTimeStamp());
        jobpost.setExpireDate(getTimeStamp());
        return jobPostRepository.save(jobpost);
    }
    @RequestMapping(value = "/repost",method = RequestMethod.POST)
    public JobPost rePost(@RequestBody JobPost jobpost) {
      jobpost.setIsPublish(false);
        jobpost.setPostedDateTime(getTimeStamp());
        if((jobpost.getExpireDate()).compareTo(getTimeStamp()) < 0)
            jobpost.setExpireDate(getTimeStamp());

        this.updateJobPost(jobpost,jobpost.getJobId());
        return jobpost;
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
        JobPost jobPost =jobPostRepository.findByJobId(id);
        return jobPost.getTitle();
    }

    @RequestMapping(value = "/all/user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<JobPost> getAllPostedJobsByUserId(@PathVariable long id) {
        return jobPostRepository.findByProviderId(id);
    }
    public Date getTimeStamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public Boolean publishJobPost(@RequestBody JobPost jobPost) {
        if((jobPost.getExpireDate()).compareTo(getTimeStamp()) >= 0) {
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
    public String findUserName(@PathVariable (value = "id") long id){
       Optional<JobApplication> optional= jobApplicationRepository.findById(id);
       JobApplication  ja;
       ja=optional.get();
       JobPost jp= jobPostRepository.findByJobId(ja.getJobId());
       return jp.getTitle();
    }



}
