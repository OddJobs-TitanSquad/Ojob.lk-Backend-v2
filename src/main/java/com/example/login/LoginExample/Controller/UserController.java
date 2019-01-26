package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Exception.ResourceNotFoundException;
import com.example.login.LoginExample.Models.Complain;
import com.example.login.LoginExample.Models.Notify;
import com.example.login.LoginExample.Models.Rate;
import com.example.login.LoginExample.Models.User;
import com.example.login.LoginExample.PayLoad.*;

import com.example.login.LoginExample.Repository.ComplainRepository;
import com.example.login.LoginExample.Repository.RateRepository;
import com.example.login.LoginExample.Repository.UserRepository;

import com.example.login.LoginExample.Security.*;

import com.example.login.LoginExample.Security.JwtTokenProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ComplainRepository complainRepository;

    @Autowired
    private RateRepository rateRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
   // @PreAuthorize("hasRole('ROLE_USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/me/{token}")
    public Optional<User>  whoami(@PathVariable(value = "token") String token) {
        return userRepository.findById(jwtTokenProvider.getUserIdFromJWT(token));
    }

    @GetMapping("/user/id/{token}")
    public long myId(@PathVariable(value="token") String token){
        return jwtTokenProvider.getUserIdFromJWT(token);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));



        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

        return userProfile;
    }





    @PostMapping("/user/complain")
    public void makeComplain(@RequestBody Complain complain){
        complainRepository.save(complain);
    }

    @GetMapping("/user/profile/{id}")
    public Optional<User>  UserProfile(@PathVariable(value = "id") long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/user/ratings")
    public void makeRate(@RequestBody Rate rate){
        rateRepository.save(rate);
    }

    @GetMapping("/user/ratings/{personalId}")
    public long fiveStars(@PathVariable(value = "personalId") long personalId) {
       long stars = rateRepository.fiveStars(personalId);
       return stars;
    }

    @GetMapping("/user/userName/{id}")
    public String findUserName(@PathVariable (value = "id") long id){
        Optional <User> optional=userRepository.findById(id);
        User user;
        user= optional.get();
        return user.getUsername();
    }
}
