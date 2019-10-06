package xin.lz1998.wcads;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

@EnableScheduling
@SpringBootApplication
public class WcaDsApplication {

    public static void main(String[] args) {
        initTray();
        SpringApplication.run(WcaDsApplication.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
    public static void initTray(){
        // 托盘图标，方便退出
        if(SystemTray.isSupported()){
            // 退出菜单
            MenuItem exitItem =new MenuItem("Exit");
            exitItem.addActionListener(e -> System.exit(0));

            PopupMenu popupMenu=new PopupMenu();
            popupMenu.add(exitItem);

            // 创建图标
            URL url=WcaDsApplication.class.getResource("/icon.jpg");
            ImageIcon icon=new ImageIcon(url);
            TrayIcon trayIcon=new TrayIcon(icon.getImage(),"WCA-Data-Service",popupMenu);
            trayIcon.setImageAutoSize(true);
            SystemTray systemTray=SystemTray.getSystemTray();
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println(e.toString());
            }
        }
    }
}
