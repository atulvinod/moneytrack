package com.atulvinod.moneytrack;

import java.util.Locale;

public class IndiaCurrencyFormatter {
    java.text.NumberFormat format;
    public IndiaCurrencyFormatter(){
        Locale indiaLocale = new Locale("en", "IN");
        format = java.text.NumberFormat.getCurrencyInstance(indiaLocale);
    }
    public String formatAmount(int Amount){
        return format.format(Amount).toString();
    }
}
