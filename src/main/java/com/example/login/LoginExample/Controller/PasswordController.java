package com.example.login.LoginExample.Controller;


import com.example.login.LoginExample.Models.User;
import com.example.login.LoginExample.Repository.UserRepository;
import com.example.login.LoginExample.Services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.MulticastSocket;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class PasswordController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

    @PostMapping("/user/forgot/{email}")
    public void getAllJobs(@PathVariable(value = "email") String email){
         JavaMailSender javaMailSender;
       Optional<User> optional=userRepository.findByEmail(email);

        User user = optional.get();
        user.setResetToken(UUID.randomUUID().toString());

        userRepository.save(user);

        mailService.sendEmail(user);

    }

    @PostMapping("/user/forgotEmail")
    public void makeComplain(@RequestBody String password,String resettoken ){
        Optional<User> optional=userRepository.findByResetToken(resettoken);
        User user=optional.get();
        user.setResetToken(null);
        user.setPassword(passwordEncoder.encode(password));


    }

}
