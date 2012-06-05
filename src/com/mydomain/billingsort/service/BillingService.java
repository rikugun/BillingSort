package com.mydomain.billingsort.service;

import java.math.BigInteger;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

import com.mydomain.billingsort.bo.Root;
import com.mydomain.billingsort.vo.User;

public class BillingService {
    // 保存所有待处理话单 一个队列一个文件
    static final BlockingQueue<Root> callBillings = new LinkedBlockingQueue<Root>();
    //话单文件队列  一个文件一个队列
    static final BlockingQueue<URL> fileQueue = new LinkedBlockingQueue<URL>();


    // 根据设备号码保存话单
    static final ConcurrentMap<BigInteger, User> usersMap = new ConcurrentHashMap<BigInteger, User>();

    //如果考虑全局用一个set就用这个,现在是一个文件处理一次就打印一次
    //总局排序列表
    static final Set<BigInteger> allUsers = new CopyOnWriteArraySet<BigInteger>();
    // 南京分局 设备号码列表
    static final Set<BigInteger> nanjingUsers = new CopyOnWriteArraySet<BigInteger>();
    // 南通分局 设备号码列表
    static final Set<BigInteger> nantongUsers = new CopyOnWriteArraySet<BigInteger>();

    public static BlockingQueue<URL> getFileQueue() {
        return fileQueue;
    }
    
    public static BlockingQueue<Root> getCallbillings() {
        return callBillings;
    }

    public static ConcurrentMap<BigInteger, User> getUsersmap() {
        return usersMap;
    }

    public static Set<BigInteger> getAllusers() {
        return allUsers;
    }

    public static Set<BigInteger> getNanjingusers() {
        return nanjingUsers;
    }

    public static Set<BigInteger> getNantongusers() {
        return nantongUsers;
    }

    public static void printBills(BigInteger telephone) {
        User userBills = usersMap.get(telephone);
        System.out.println("电话号码\t用户名\t地址\t通话时间\t话费总额");
        System.out.println(userBills.toString());
    }

    public static void printAllBills(Set<BigInteger> allUser,Set<BigInteger> nanjingUser,Set<BigInteger> nantongUser) {
        System.out.println("电话号码\t用户名\t地址\t通话时间\t话费总额");
        //List<String> telephones = new ArrayList<String>(usersMap.keySet());
        //Collections.sort(telephones);
        System.out.println("电话总局");
        for (BigInteger telephone : allUser) {
            System.out.println(usersMap.get(telephone));
        }
        System.out.println("南京分局");
        for (BigInteger telephone : nanjingUser) {
            System.out.println(usersMap.get(telephone));
        }
        System.out.println("南通分局");
        for (BigInteger telephone : nantongUser) {
            System.out.println(usersMap.get(telephone));
        }
    }

}
