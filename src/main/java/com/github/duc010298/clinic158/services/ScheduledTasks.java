package com.github.duc010298.clinic158.services;

import com.github.duc010298.clinic158.entity.TokenEntity;
import com.github.duc010298.clinic158.repository.CustomerRepository;
import com.github.duc010298.clinic158.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ScheduledTasks {

    private TokenRepository tokenRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public ScheduledTasks(TokenRepository tokenRepository, CustomerRepository customerRepository) {
        this.tokenRepository = tokenRepository;
        this.customerRepository = customerRepository;
    }

    //delete customers who visited 1 year ago
    //At 23:00:00pm, on every Tuesday, every month
    @Scheduled(cron = "0 0 23 ? * FRI")
    public void deleteOldCustomer() {
        Date currentDate = new Date();
        long millisecondsInOneYear = (long) 365 * 24 * 60 * 60 * 1000;
        Date oneYearBefore = new Date(currentDate.getTime() - millisecondsInOneYear);
        customerRepository.deleteCustomerBeforeDay(oneYearBefore);
    }

    //change Token
    //At second :00 of minute :30 of every hour
    @Scheduled(cron = " 0 30 * ? * *")
    public void changeToken() {
        TokenEntity tokenEntity = tokenRepository.findById(1);
        String newToken = UUID.randomUUID().toString().replace("-", "");
        tokenEntity.setToken(newToken);
        tokenRepository.save(tokenEntity);
    }

    //backup database
    //At 00:00:00am every day
//    @Scheduled(cron = "0 0 0 ? * *")
//    public void backupDatabase() {
//        //call function to backup database here
//    }

}
