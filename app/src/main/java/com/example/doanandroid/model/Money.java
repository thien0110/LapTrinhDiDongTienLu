package com.example.doanandroid.model;

public class Money {
    private int iD;
    private int money;

    public Money(int iD, int money) {
        this.iD = iD;
        this.money = money;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
