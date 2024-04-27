package com.emp.utils;

import org.springframework.mail.SimpleMailMessage;

public class EmailUtil {
    public static SimpleMailMessage prepareEmail(String toEmail,String subject,String content){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom("portalemployeeservice@gmail.com");
        return simpleMailMessage;
    }
}
