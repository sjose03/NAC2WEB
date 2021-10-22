package com.example.nac2.controller;



import com.example.nac2.dto.EmailDto;
import com.example.nac2.model.EmailModel;
import com.example.nac2.service.EmailService;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto,emailModel);
        emailService.sendEmail(emailModel);

        return new ResponseEntity<>(emailModel, HttpStatus.OK);
    }

    @GetMapping("/get-emails")
    public ResponseEntity<List<EmailModel>> getEmail(){
        List<EmailModel> emailModelList = emailService.getAllEmails();

        return new ResponseEntity<>(emailModelList,HttpStatus.OK);
    }
}
