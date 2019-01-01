package com.atulvinod.moneytrack;

public class Lending_EntityData {

    int id,amount;

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Lending_EntityData(int id,int am){
        this.id=id;
        this.amount = am;
    }
}
