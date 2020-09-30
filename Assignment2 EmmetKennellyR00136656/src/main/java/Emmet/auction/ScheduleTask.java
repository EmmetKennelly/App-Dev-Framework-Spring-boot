package Emmet.auction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Emmet.auction.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import Emmet.auction.dao.JobDao;
import Emmet.auction.domain.Job;

@Component
@EnableScheduling
public class ScheduleTask {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
	JobService jobService;

    @Autowired
    JobDao jobDao;


//    @Scheduled(cron = "0 0 0 0 1 ?")
    @Scheduled(cron = "*/5 * * * * *")
    public void deleteOldJobs() {
        Date curTime = new Date();
        Date twentyDaysAgo = new Date(curTime.getTime() - 1000 * 60 * 60 * 24 * 20);
//        Date twentyDaysAgo = new Date(curTime.getTime() - 20000); for testing
        List<Job> allJobs = jobService.findByisOpen(true);
		List<Job> oldJobs = allJobs.stream().filter(job -> {return twentyDaysAgo.getTime() > job.getJobStartedEpoch(); }).collect(Collectors.toList());
		oldJobs.forEach(job -> job.setOpen(false));
    }
}
