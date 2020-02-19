package com.vajun.admin.config;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@RefreshScope
public class ReceiveConfig {


    /*@Value("${spring.version}")
    private String port;

    public String config(){
        return port;
    }*/

}
