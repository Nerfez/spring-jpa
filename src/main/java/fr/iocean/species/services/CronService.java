package fr.iocean.species.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CronService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${cron.expression}")
    public void executeCronTask() {
        System.out.println("Cron Temps : " + dateFormat.format(new Date()));
    }
}
