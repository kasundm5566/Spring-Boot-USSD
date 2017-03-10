package com.spring.boot;

import hms.kite.samples.api.ussd.messages.MoUssdReq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@Controller
public class SpringBootUssdApplication {

    @RequestMapping("/")
    @ResponseBody
    String init(MoUssdReq moUssdReq) {
        return "App started";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUssdApplication.class, args);
    }
}
