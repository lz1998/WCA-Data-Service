package xin.lz1998.wcads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WcaDsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcaDsApplication.class, args);
    }

}
