package com.github.duc010298.clinic158.services;

import com.github.duc010298.clinic158.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {

    private CustomerRepository customerRepository;

    @Autowired
    public ScheduledTasks(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //delete customers who visited 1 year ago
    //At 23:00:00pm, on every Tuesday, every month
    //@Scheduled(cron = "0 0 23 ? * TUE")
    //TODO: for test delete on server
    @Scheduled(cron = "0 38 23 ? * FRI")
    public void deleteOldCustomer() {
//        Date currentDate = new Date();
//        long millisecondsInOneYear = (long) 365 * 24 * 60 * 60 * 1000;
//        Date oneYearBefore = new Date(currentDate.getTime() - millisecondsInOneYear);
//        customerHiddenRepository.deleteCustomerBeforeDay(oneYearBefore);

        Date currentDate = new Date();
        long millisecondsInOneYear = (long) 3600000;
        Date oneYearBefore = new Date(currentDate.getTime() - millisecondsInOneYear);
        customerRepository.deleteCustomerBeforeDay(oneYearBefore);
    }

    //backup database
    //At 00:00:00am every day
//    @Scheduled(cron = "0 0 0 ? * *")
//    public void backupDatabase() {
//        //call function to backup database here
//    }

}
