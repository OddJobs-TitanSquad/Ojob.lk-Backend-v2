package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.CompletedJob;
import com.example.login.LoginExample.Repository.CompletedJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
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

    // user seeker knk wen welawta
    @GetMapping("/seeker/{userId}")
    public List<CompletedJob> completeSeeker(@PathVariable(value = "userId") long userId) {
        List<CompletedJob>  seeker= completedJobRepository.findBySeekerId(userId);
        return seeker;
    }

    // user provider knk wen welwt
    @GetMapping("/provider/{userId}")
    public List<CompletedJob> completeProvider(@PathVariable(value = "userId") long userId) {
        List<CompletedJob>  provider= completedJobRepository.findByProviderId(userId);
        return provider;
    }

    @GetMapping("/countProvider/{userId}")
    public int countProvider(@PathVariable(value = "userId") long userId) {
        int  provider= completedJobRepository.CountByProvider(userId);
        return provider;
    }

    @GetMapping("/countSeeker/{userId}")
    public int countSeeker(@PathVariable(value = "userId") long userId) {
        int  seeker= completedJobRepository.CountBySeeker(userId);
        return seeker;
    }
}
