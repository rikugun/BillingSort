package com.mydomain.billingsort.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import com.mydomain.billingsort.bo.Call;
import com.mydomain.billingsort.bo.Root;
import com.mydomain.billingsort.vo.User;

public class SumUserChargeService implements Runnable {

    // 待处理队列
    private final BlockingQueue<Root> queue = BillingService.getCallbillings();
    // Set<BigInteger> allUsers = BillingService.getAllusers();
    // Set<BigInteger> njUsers = BillingService.getNanjingusers();
    // Set<BigInteger> ntUsers = BillingService.getNantongusers();
    Map<BigInteger, User> userMap = BillingService.getUsersmap();

    Set<BigInteger> billUsers = new HashSet<BigInteger>();
    Set<BigInteger> njUsers = new HashSet<BigInteger>();
    Set<BigInteger> ntUsers = new HashSet<BigInteger>();

    @Override
    public void run() {
        try {
            while (true) {
                Root root = queue.take();
                for (Call call : root.getCall()) {
                    billUsers.add(call.getTelephone());
                    User user = userMap.get(call.getTelephone());
                    if (user == null) {
                        user = new User();
                        user.setTelephone(call.getTelephone());
                        user.setName(call.getName());
                        user.setAddress(call.getAddress());
                        user.setTotalCallTime(new BigInteger("0"));
                        user.setTotalCharge(new BigDecimal("0.00"));
                    }
                    user.addFee(call);
                    userMap.put(call.getTelephone(), user);
                    if ("南京".equals(call.getAddress())) {
                        njUsers.add(call.getTelephone());
                    } else if ("南通".equals(call.getAddress())) {
                        ntUsers.add(call.getTelephone());
                    }
                }
                sortSet(billUsers);
                sortSet(njUsers);
                sortSet(ntUsers);
                BillingService.printAllBills(billUsers, njUsers, ntUsers);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }

    }

    /**
     * 将Set转换为List 后排序
     * 
     * @param sets
     */
    private void sortSet(Set<BigInteger> sets) {
        List<BigInteger> list = new ArrayList<BigInteger>(sets);
        Collections.sort(list);
    }

}
