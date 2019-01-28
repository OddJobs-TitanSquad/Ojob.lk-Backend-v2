package com.example.login.LoginExample.Controller;

import com.example.login.LoginExample.Models.JobApplication;
import com.example.login.LoginExample.Models.NotifyProvider;
import com.example.login.LoginExample.Repository.NotifyProviderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/user/NotifyProvider")
public class NotifyProviderController {

    private NotifyProviderRepository np;

    @GetMapping("/provider/{userId}")
    public List<NotifyProvider> completeSeeker(@PathVariable(value = "userId") long userId) {
        List<NotifyProvider> Notifies = np.NotifyProvider(userId);
        return Notifies;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") long id) {
       Optional<NotifyProvider>  notipro=np.findById(id);

        np.delete(notipro.get());

    }
}
