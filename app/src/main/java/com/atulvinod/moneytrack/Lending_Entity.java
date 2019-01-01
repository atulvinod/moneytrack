package com.atulvinod.moneytrack;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

@android.arch.persistence.room.Entity(tableName = "lending_table")
public class Lending_Entity {

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name="amount")
    private int mAmount;

    /*YOU_OWE_THEM :- if you owe them*/
    /*THEY_OWE_YOU :- if they owe you*/
    @ColumnInfo(name = "ownership")
    private String ownership;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="mID")
    private int mID;


    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public Lending_Entity(int amount, String name, String ownership, int mID){
        this.ownership = ownership;

        this.mAmount=amount;
        this.mID = mID;
        this.mName = name;
    }
    public String getName(){
        return this.mName;
    }

    public int getID(){return this.mID;}

    public void setName(String name){
        this.mName = name;
    }
    public void setID(int id){
        this.mID = id;
    }
    public int getAmount() {
        return mAmount;
    }

    public void setAmount(int amount) {
        mAmount = amount;
    }
}
