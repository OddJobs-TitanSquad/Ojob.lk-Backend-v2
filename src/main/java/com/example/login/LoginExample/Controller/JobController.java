package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.Job;
import com.example.login.LoginExample.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class JobController {

    @Autowired
    private JobRepository jobRepository;
    @GetMapping("/user/completed/{sid}")
 public List<Job> complete(@PathVariable(value = "sid") long sid) {
    List<Job> jobs= jobRepository.findUserCompleted(sid);
    return jobs;
 }


    @GetMapping("/user/provided/{pid}")
    public List<Job> provide(@PathVariable(value = "pid") long pid) {
        List<Job> jobs= jobRepository.findUserProvided(pid);
        return jobs;
    }


    @RequestMapping("/user/topics")
    public String getAllJobs(){
        return "All jobs!!!";
    }

    @GetMapping("/user/job/{id}")
    public Optional<Job> getJob(@PathVariable(value = "id") long id){
        Optional<Job> job=jobRepository.findById(id);
        return  job;
    }

}
