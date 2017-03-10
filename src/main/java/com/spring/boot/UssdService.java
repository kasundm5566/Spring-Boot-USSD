package com.spring.boot;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.ussd.OperationType;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dinesh on 3/9/17.
 */
@Controller
public class UssdService {

    private static final String SERVICE_EXIT_CODE = "000";
    private static final String SERVICE_PREV_CODE = "999";
    private static final String SERVICE_INIT_CODE = "#123#";
    private static final String SERVICE_ELECTRONICS_CODE = "1";
    private static final String SERVICE_COSMETICS_CODE = "2";
    private static final String SERVICE_HOUSEHOLDS_CODE = "3";
    private static final String REQUEST_SEND_URL = "http://localhost:7000/ussd/send";
    private static final String OPERATION_MT_CONT = "mt-cont";
    private static final String OPERATION_MT_FIN = "mt-fin";

    private static ArrayList<String> menuStates = new ArrayList<>();

    @RequestMapping("/springussd")
    @ResponseBody
    public void onReceivedUssd(@RequestBody MoUssdReq moUssdReq) {
        try {
            processRequest(moUssdReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SdpException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(MoUssdReq moUssdReq) throws MalformedURLException, SdpException {
        MtUssdReq mtUssdReq = new MtUssdReq();
        mtUssdReq.setApplicationId("APP_000001");
        mtUssdReq.setPassword("dfc0333b82a8e01f500e7e37188f97eo");
        mtUssdReq.setDestinationAddress(moUssdReq.getSourceAddress());
        String message;

        switch (moUssdReq.getMessage()){
            case SERVICE_INIT_CODE:
                message = "Welcome to the app.\nPlease select an option.\n";
                message += "1. Electronics\n2. Cosmetics\n3. Households\n";
                message+="999:Back\n000:Exit";
                mtUssdReq.setMessage(message);
                mtUssdReq.setSessionId(moUssdReq.getSessionId());
                mtUssdReq.setUssdOperation(OPERATION_MT_CONT);
                menuStates.add(moUssdReq.getMessage());
                break;
            case SERVICE_EXIT_CODE:
                message = "Thank you for using the service.";
                mtUssdReq.setMessage(message);
                mtUssdReq.setUssdOperation(OPERATION_MT_FIN);
                menuStates.clear();
                break;
            case SERVICE_ELECTRONICS_CODE:
                message = "Electronics\n";
                message += "1. Television\n2. Radio\n3. DVD players\n4. Mobile phones\n";
                message+="999:Back\n000:Exit";
                mtUssdReq.setMessage(message);
                mtUssdReq.setSessionId(moUssdReq.getSessionId());
                mtUssdReq.setUssdOperation(OPERATION_MT_CONT);
                menuStates.add(moUssdReq.getMessage());
                break;
            case SERVICE_COSMETICS_CODE:
                message = "Electronics\n";
                message += "1. Television\n2. Radio\n3. DVD players\n4. Mobile phones\n";
                message+="999:Back\n000:Exit";
                mtUssdReq.setMessage(message);
                mtUssdReq.setSessionId(moUssdReq.getSessionId());
                mtUssdReq.setUssdOperation(OPERATION_MT_CONT);
                menuStates.add(moUssdReq.getMessage());
                break;
            default:
                message="Invalid input\n999:Back";
        }

        UssdRequestSender ussdRequestSender = new UssdRequestSender(new URL(REQUEST_SEND_URL));
        ussdRequestSender.sendUssdRequest(mtUssdReq);
        System.out.println(menuStates);
    }
}
