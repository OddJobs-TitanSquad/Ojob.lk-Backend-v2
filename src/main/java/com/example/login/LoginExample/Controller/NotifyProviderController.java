package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobApplication;
import com.example.login.LoginExample.Models.NotifyProvider;
import com.example.login.LoginExample.Repository.NotifyProviderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/NotifySeeker")
public class NotifyProviderController {

    private NotifyProviderRepository np;

    @GetMapping("/seeker/{userId}")
    public List<NotifyProvider> completeSeeker(@PathVariable(value = "userId") long userId) {
        List<NotifyProvider> Notifies = np.NotifyProvider(userId);
        return Notifies;
    }

    @RequestMapping(value = "/notifyProvider/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody NotifyProvider notifyProvider) {

        np.delete(notifyProvider);


    }
}
