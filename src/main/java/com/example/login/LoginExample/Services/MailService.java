package com.example.login.LoginExample.Services;

import com.example.login.LoginExample.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    /**
     *
     * @param javaMailSender
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user)  {

        String appUrl="http://localhost:4200/resetPassword";
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("dmv1995vitha@gmail.com");
        mail.setSubject("Password Reset Request");
        mail.setText("To reset your password, click the link below:\n" + appUrl
                + "/" + user.getResetToken());

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }


}
