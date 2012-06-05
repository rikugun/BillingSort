package com.mydomain.billingsort.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.mydomain.billingsort.bo.Call;
import com.mydomain.billingsort.bo.Root;

public class TestRoot {

    @Test
    public void testGetCall() throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(com.mydomain.billingsort.bo.ObjectFactory.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        @SuppressWarnings("unchecked")
        JAXBElement<Root> billing = (JAXBElement<Root>) unmarshaller.unmarshal(this.getClass().getResource("/billings.xml"));
        Root root = billing.getValue();
        for (Call call : root.getCall()) {
            System.out.println(call.getTelephone()+"");
            System.out.println("通话时间:"+call.getEndtime().compare(call.getStarttime()));
        }
    }

}
