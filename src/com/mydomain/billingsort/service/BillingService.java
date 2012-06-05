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
    // �������д������� һ������һ���ļ�
    static final BlockingQueue<Root> callBillings = new LinkedBlockingQueue<Root>();
    //�����ļ�����  һ���ļ�һ������
    static final BlockingQueue<URL> fileQueue = new LinkedBlockingQueue<URL>();


    // �����豸���뱣�滰��
    static final ConcurrentMap<BigInteger, User> usersMap = new ConcurrentHashMap<BigInteger, User>();

    //�������ȫ����һ��set�������,������һ���ļ�����һ�ξʹ�ӡһ��
    //�ܾ������б�
    static final Set<BigInteger> allUsers = new CopyOnWriteArraySet<BigInteger>();
    // �Ͼ��־� �豸�����б�
    static final Set<BigInteger> nanjingUsers = new CopyOnWriteArraySet<BigInteger>();
    // ��ͨ�־� �豸�����б�
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
        System.out.println("�绰����\t�û���\t��ַ\tͨ��ʱ��\t�����ܶ�");
        System.out.println(userBills.toString());
    }

    public static void printAllBills(Set<BigInteger> allUser,Set<BigInteger> nanjingUser,Set<BigInteger> nantongUser) {
        System.out.println("�绰����\t�û���\t��ַ\tͨ��ʱ��\t�����ܶ�");
        //List<String> telephones = new ArrayList<String>(usersMap.keySet());
        //Collections.sort(telephones);
        System.out.println("�绰�ܾ�");
        for (BigInteger telephone : allUser) {
            System.out.println(usersMap.get(telephone));
        }
        System.out.println("�Ͼ��־�");
        for (BigInteger telephone : nanjingUser) {
            System.out.println(usersMap.get(telephone));
        }
        System.out.println("��ͨ�־�");
        for (BigInteger telephone : nantongUser) {
            System.out.println(usersMap.get(telephone));
        }
    }

}
