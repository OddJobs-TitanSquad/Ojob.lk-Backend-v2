package com.example.login.LoginExample.Controller;


import com.example.login.LoginExample.Exception.ResourceNotFoundException;
import com.example.login.LoginExample.Models.User;
import com.example.login.LoginExample.PayLoad.EditRequest;
import com.example.login.LoginExample.Repository.UserRepository;
import com.example.login.LoginExample.Services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/user/forgot/{email}")
    public void getAllJobs(@PathVariable(value = "email") String email){
         JavaMailSender javaMailSender;
       Optional<User> optional=userRepository.findByEmail(email);
        System.out.println("hgsghdh");
        User user = optional.get();
        user.setResetToken(UUID.randomUUID().toString());

        userRepository.save(user);

        mailService.sendEmail(user);
        System.out.println(user.getEmail());
    }

    @GetMapping("/user/forgotEmail/{password}/{resettoken}")
    public void makeComplain(@PathVariable(value = "password") String password,@PathVariable(value = "resettoken") String resettoken ){
        Optional<User> optional=userRepository.findByResetToken(resettoken);
        User user=optional.get();
        user.setResetToken(null);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);


    }


    @PutMapping("/Update/{id}")
    public User updateProfile(@PathVariable(value = "id") Long id,
                              @Valid @RequestBody EditRequest userDetails) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));



        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

}
