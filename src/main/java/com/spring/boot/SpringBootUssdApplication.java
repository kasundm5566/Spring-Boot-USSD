package com.spring.boot;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@SpringBootApplication
@Controller
public class SpringBootUssdApplication implements WebApplicationInitializer {

    @RequestMapping("/")
    @ResponseBody
    String init(MoUssdReq moUssdReq) {
/*        try {
            MtSmsReq mtSmsReq = new MtSmsReq();
            mtSmsReq.setDestinationAddresses(Arrays.asList("tel:771234567"));
            mtSmsReq.setMessage("Thank you for the payment");
            mtSmsReq.setApplicationId("APP_000001");
            mtSmsReq.setPassword("dfc0333b82a8e01f500e7e37188f97eo");
            URL url = new URL("http://localhost:7000/sms/send");
            SmsRequestSender requestSender = new SmsRequestSender(url);
            MtSmsResp smsResp = requestSender.sendSmsRequest(mtSmsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
/*        try {
            UssdRequestSender ussdRequestSender=new UssdRequestSender(new URL("http://127.0.0.1:7000/ussd/send"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        return "App started";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootUssdApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
/*        System.out.println("ran");
        ServletRegistration.Dynamic h2Servlet = servletContext.addServlet("h2Servlet", (Servlet) new UssdListener());
        h2Servlet.setLoadOnStartup(1);
        h2Servlet.addMapping("/springussd");*/
    }
}
