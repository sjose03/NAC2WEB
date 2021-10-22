package com.example.nac2.repository;

import com.example.nac2.model.EmailModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<EmailModel,String> {
}
