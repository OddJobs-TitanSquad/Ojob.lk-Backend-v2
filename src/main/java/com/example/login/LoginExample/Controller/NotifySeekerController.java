package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.NotifyProvider;
import com.example.login.LoginExample.Models.NotifySeeker;
import com.example.login.LoginExample.Repository.NotifySeekerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/NotifySeeker")
public class NotifySeekerController {

 private NotifySeekerRepository ns;

    @GetMapping("/seeker/{userId}")
    public List<NotifySeeker> completeSeeker(@PathVariable(value = "userId") long userId) {
        List<NotifySeeker>  Notifies= ns.NotifySeeker(userId);
        return Notifies;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") long id) {
        Optional<NotifySeeker> notipro=ns.findById(id);

        ns.delete(notipro.get());

    }
}
