package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobType;
import com.example.login.LoginExample.Repository.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/job-types")
public class JobTypeController {
    @Autowired
    private final JobTypeRepository jobTypeRepository;
    public JobTypeController(JobTypeRepository jobTypeRepository){
        this.jobTypeRepository=jobTypeRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public JobType createJobType(@RequestBody JobType jobType) {
        return jobTypeRepository.save(jobType);
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    //   @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody
    Iterable<JobType> getAllJobTypes() {
        return jobTypeRepository.findAll();
    }
}
