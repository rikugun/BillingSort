package com.mydomain.billingsort.service;

import java.net.URL;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.mydomain.billingsort.bo.Root;

public class UnmarshallService implements Runnable {
    private static final int MAX_DUE_COUNT = 10;
    private static final int WAIT_TIME = 10 * 1000;

    // 待处理队列
    private Root callRoot;
    Unmarshaller unmarshaller;
    Logger logger = Logger.getLogger(this.getClass().getName());

    public UnmarshallService() {

        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(com.mydomain.billingsort.bo.ObjectFactory.class);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                if (BillingService.getCallbillings().size() > MAX_DUE_COUNT) {
                    Thread.sleep(WAIT_TIME);
                    continue;
                }
                URL billFile;
                billFile = BillingService.getFileQueue().take();
                try {
                    @SuppressWarnings("unchecked")
                    JAXBElement<Root> billing = (JAXBElement<Root>) unmarshaller.unmarshal(billFile);
                    callRoot = billing.getValue();
                    BillingService.getCallbillings().add(callRoot);
                } catch (JAXBException e) {
                    logger.warning("解析话单文件错误:" + billFile);
                    continue;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }
}
