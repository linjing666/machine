package wdi.lab.machine.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wdi.lab.machine.service.LabSevice;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * @program: machine
 * @description
 * @author: linjing
 * @create: 2020-01-10 12:03
 **/
@Component
public class SchedulerTask {
    @Autowired
    private LabSevice labSevice;
    @Scheduled(cron="0 0/5 * * * ? ")
    private void process(){
        try {
            labSevice.getAllInformatin();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}