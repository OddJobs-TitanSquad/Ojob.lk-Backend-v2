package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.NotifyProvider;
import com.example.login.LoginExample.Models.NotifySeeker;
import com.example.login.LoginExample.Repository.NotifySeekerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/NotifySeeker")
public class NotifySeekerController {

 private NotifySeekerRepository ns;

    @GetMapping("/provider/{userId}")
    public List<NotifySeeker> completeSeeker(@PathVariable(value = "userId") long userId) {
        List<NotifySeeker>  Notifies= ns.NotifySeeker(userId);
        return Notifies;
    }

    @RequestMapping(value = "/notifyProvider/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody NotifySeeker notifySeeker) {

        ns.delete(notifySeeker);


    }
}
