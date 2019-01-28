package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.Rate;
import com.example.login.LoginExample.Repository.RateRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RequestMapping("api/user/rate")
public class RateController {

    private RateRepository rr;

    @GetMapping("/FiveRate/{personalId}")
    public long CountFiveStar(@PathVariable(value = "personalId") long personalId) {
        long stars = rr.CountFiveStar(personalId);
        return stars;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Rate createCompletedJob(@RequestBody Rate rate){

        return rr.save(rate);
    }
}
