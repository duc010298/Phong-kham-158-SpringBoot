package com.github.duc010298.clinic158.services;

import com.github.duc010298.clinic158.repository.CustomerHiddenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {

    private CustomerHiddenRepository customerHiddenRepository;

    @Autowired
    public ScheduledTasks(CustomerHiddenRepository customerHiddenRepository) {
        this.customerHiddenRepository = customerHiddenRepository;
    }

    //delete customers who visited 1 year ago
    //At 23:00:00pm, on every Tuesday, every month
    //@Scheduled(cron = "0 0 23 ? * TUE")
    //TODO: for test delete on server
    @Scheduled(cron = "0 30 9 ? * FRI")
    public void deleteOldCustomer() {
//        Date currentDate = new Date();
//        long millisecondsInOneYear = (long) 365 * 24 * 60 * 60 * 1000;
//        Date oneYearBefore = new Date(currentDate.getTime() - millisecondsInOneYear);
//        customerHiddenRepository.deleteCustomerBeforeDay(oneYearBefore);

        Date currentDate = new Date();
        long millisecondsInOneYear = (long) 3600000;
        Date oneYearBefore = new Date(currentDate.getTime() - millisecondsInOneYear);
        customerHiddenRepository.deleteCustomerBeforeDay(oneYearBefore);
    }

    //backup database
    //At 00:00:00am every day
//    @Scheduled(cron = "0 0 0 ? * *")
//    public void backupDatabase() {
//        //call function to backup database here
//    }

}
