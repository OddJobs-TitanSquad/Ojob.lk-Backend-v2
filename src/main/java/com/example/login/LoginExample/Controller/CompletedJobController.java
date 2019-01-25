package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.CompletedJob;
import com.example.login.LoginExample.Repository.CompletedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/completed-job")
public class CompletedJobController {
    @Autowired
    private final CompletedJobRepository completedJobRepository;

    public CompletedJobController(CompletedJobRepository completedJobRepository) {
        this.completedJobRepository=completedJobRepository;
    }
    @RequestMapping(method = RequestMethod.POST)
    public CompletedJob createCompletedJob(@RequestBody CompletedJob completedJob){
        completedJob.setCompletedDateTime(getTimeStamp());
        return completedJobRepository.save(completedJob);
    }
    public Date getTimeStamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
