package com.atulvinod.moneytrack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "transaction_table")
public class Transaction_Record {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    int ID;

    @ColumnInfo
    int amount;

    @ColumnInfo
    String day;

    @ColumnInfo
    String month;



    @ColumnInfo
    String year;

    @ColumnInfo
    String transaction_type;

    @ColumnInfo
    String transaction_flags;

    @ColumnInfo
    String time;

    @ColumnInfo
    String discription;

    //Getters and setters
    public Transaction_Record(int amount,String day,String month,String year,String transaction_type,String transaction_flags,String time,String discription,int ID){
        this.amount = amount;
        this.day=day;
        this.month = month;
        this.year = year;
        this.transaction_type = transaction_type;
        this.transaction_flags = transaction_flags;
        this.time = time;
        this.ID = ID;
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_flags() {
        return transaction_flags;
    }

    public void setTransaction_flags(String transaction_flags) {
        this.transaction_flags = transaction_flags;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
