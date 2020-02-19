package com.vajun.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class RestHiController {

    @Value("${test.version}")
    private String version;

    @GetMapping(value = "/hello")
    public String getSpringVersion() {
        return "SpringVersion:" +version;
    }
}