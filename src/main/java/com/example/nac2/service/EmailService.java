package com.example.nac2.service;

import com.example.nac2.model.EmailModel;
import com.example.nac2.model.StatusEmail;
import com.example.nac2.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

//    @Autowired
    private JavaMailSender javaMailSender;


    public EmailModel sendEmail(EmailModel emailModel){
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject((emailModel.getSubject()));
            message.setText(emailModel.getText());

            javaMailSender.send(message);
            emailModel.setStatusEmail(StatusEmail.SENT);
        }
        catch (MailException ex){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }
        finally {
            return emailRepository.save(emailModel);
        }
    }

    public List<EmailModel> getAllEmails(){
        return emailRepository.findAll();
    }

}
