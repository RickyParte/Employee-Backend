package com.emp.service;

import com.emp.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmailNotificationToManager1(String toManagerEMail, String subject, String emailContent) {
        javaMailSender.send(EmailUtil.prepareEmail(toManagerEMail,subject,emailContent));
    }
}
