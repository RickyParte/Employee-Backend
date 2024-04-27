package com.emp.service;

import org.springframework.stereotype.Service;


public interface EmailService {
    public void sendEmailNotificationToManager1(String toManagerEMail,String subject,String emailContent);

}
