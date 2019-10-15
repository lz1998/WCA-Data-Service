package xin.lz1998.wcads.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xin.lz1998.wcads.service.WcaService;


@Component
public class ScheduleTask {
    Logger logger= LoggerFactory.getLogger(ScheduleTask.class);
    @Autowired
    WcaService wcaService;

    @Scheduled(cron = "0 15 12 * * ?",zone = "Asia/Shanghai")
    public void updateWcaData(){
        logger.info("update wca data");
        wcaService.updateData();
    }
}
