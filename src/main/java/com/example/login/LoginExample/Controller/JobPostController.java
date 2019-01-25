package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobPost;
import com.example.login.LoginExample.Repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/post-jobs")
public class JobPostController {
    @Autowired
    private final JobPostRepository jobPostRepository;

    public JobPostController(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
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

}
