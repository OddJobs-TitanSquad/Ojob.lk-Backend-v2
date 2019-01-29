package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.Complain;
import com.example.login.LoginExample.Repository.ComplainRepository;
import com.sun.istack.internal.NotNull;
import org.aspectj.weaver.ast.Not;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RequestMapping("/api/user/complain")
public class ComplainController {
    private final ComplainRepository complainRepository;
    public ComplainController(ComplainRepository complainRepository){
        this.complainRepository =complainRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Boolean updateComplain(@RequestBody Complain complain, @PathVariable long id) {
        Optional<Complain> complainOptional = complainRepository.findById(id);
        if (!complainOptional.isPresent())
            return false;
        complain.setId(id);
        complainRepository.save(complain);
        return true;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Complain> getAllComplains() {
        return complainRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean DeletePaymentPackage( @PathVariable long id) {
        try{
            this.complainRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }


    }


    public Date getTimeStamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void createComplain(@RequestBody Complain complain){
        complain.setDt(getTimeStamp());
        complainRepository.save(complain);
    }

    /*@RequestMapping(value = "/replies/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Complain> complainReplies(@PathVariable long id) {
        return complainRepository.complainReplies( );
    }*/


}
