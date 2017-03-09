package com.spring.boot;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.MoSmsListener;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.ussd.MoUssdListener;
import hms.kite.samples.api.ussd.OperationType;
import hms.kite.samples.api.ussd.UssdRequestSender;
import hms.kite.samples.api.ussd.messages.MoUssdReq;
import hms.kite.samples.api.ussd.messages.MtUssdReq;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dinesh on 3/9/17.
 */
public class UssdListener implements MoUssdListener {

    @Override
    public void init() {
        try {
            UssdRequestSender ussdRequestSender=new UssdRequestSender(new URL("http://127.0.0.1:7000/ussd/send"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceivedUssd(MoUssdReq moUssdReq) {
        MtUssdReq mtUssdReq = new MtUssdReq();
        mtUssdReq.setApplicationId("APP_000001");
        mtUssdReq.setPassword("dfc0333b82a8e01f500e7e37188f97eo");
        mtUssdReq.setDestinationAddress(moUssdReq.getSourceAddress());
        mtUssdReq.setMessage("Welcome to the app");
        mtUssdReq.setSessionId(moUssdReq.getSessionId());
        mtUssdReq.setUssdOperation(OperationType.MO_CONT.getName());
        try {
            UssdRequestSender ussdRequestSender=new UssdRequestSender(new URL("http://127.0.0.1:7000/ussd/send"));
            ussdRequestSender.sendUssdRequest(mtUssdReq);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SdpException e) {
            e.printStackTrace();
        }
    }
}
