package com.mydomain.billingsort.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.mydomain.billingsort.bo.Call;

public class User {
    String name;
    BigInteger telephone;
    String address;
    BigInteger totalCallTime;
    BigDecimal totalCharge;

    public int compareTo(User o) {
        return o.telephone.compareTo(telephone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getTelephone() {
        return telephone;
    }

    public void setTelephone(BigInteger telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getTotalCallTime() {
        return totalCallTime;
    }

    public void setTotalCallTime(BigInteger totalCallTime) {
        this.totalCallTime = totalCallTime;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    /**
     * ���ظ�ʽ����Ļ���
     */
    public String toString() {
        StringBuffer sbf = new StringBuffer();
        sbf.append(this.getTelephone() + "\t");
        sbf.append(this.getName() + "\t");
        sbf.append(this.getAddress() + "\t");
        sbf.append(this.getTotalCallTime() + "\t");
        sbf.append(this.getTotalCharge().toString());
        return sbf.toString();
    }

    /**
     * �û������ۼ�
     * 
     * @param call
     */
    public void addFee(Call call) {
        this.totalCharge = this.totalCharge.add(call.getCharge());
        long thisTime = call.getEndtime().toGregorianCalendar().getTimeInMillis() - call.getStarttime().toGregorianCalendar().getTimeInMillis();
        this.totalCallTime = this.totalCallTime.add(BigInteger.valueOf(thisTime));

    }
}
