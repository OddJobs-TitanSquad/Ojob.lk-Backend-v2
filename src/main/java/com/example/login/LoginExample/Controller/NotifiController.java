package com.example.login.LoginExample.Controller;
import com.example.login.LoginExample.Models.Notification;
import com.example.login.LoginExample.Models.Notify;
import com.example.login.LoginExample.Repository.NotifiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")

public class NotifiController {
    @Autowired
    private NotifiRepository nr ;

    @GetMapping("/user/notification/{userId}")
    public List<Notification> complete(@PathVariable(value = "userId") long userId) {
        List<Notification>  notifications= nr.findNotification(userId);
        return notifications;
    }


    @GetMapping("/user/notify/{userId}")
    public List<Notify> notify(@PathVariable(value = "userId") long userId) {
        List<Notify>  notifies= nr.findNotify(userId);
        return notifies;
    }




}
