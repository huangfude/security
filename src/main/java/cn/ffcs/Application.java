package cn.ffcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Spring-Boot启动类
 * @author ScienJus
 * @date 2015/7/31.
 */
@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
