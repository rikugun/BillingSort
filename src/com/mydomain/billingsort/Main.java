package com.mydomain.billingsort;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mydomain.billingsort.service.BillingService;
import com.mydomain.billingsort.service.SumUserChargeService;
import com.mydomain.billingsort.service.UnmarshallService;

public class Main {
    static List<URL> fileList = new ArrayList<URL>();

    public static void main(String args[]) throws MalformedURLException {
        if (args.length < 1) {
            System.out.println("无效的参数!");
            useage();
            System.exit(2);
        }

        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < args.length; i++) {
            URL url = Main.class.getResource("/" + args[i]);
            //System.out.println(url.toString());
            BillingService.getFileQueue().add(url);
        }
        exec.execute(new UnmarshallService());
        exec.execute(new SumUserChargeService());

    }

    private static void useage() {
        System.out.println("java -jar billingsort.jar billings.xml ...");
    }

}
