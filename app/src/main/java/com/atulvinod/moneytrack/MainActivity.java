package com.atulvinod.moneytrack;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Tabs.OnFragmentInteractionListener,Overview.OnFragmentInteractionListener,Analysis.OnFragmentInteractionListener,Lending.OnFragmentInteractionListener,LineChartFragment.OnFragmentInteractionListener {


    Fragment tabs;
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    final static String Wallet_Money = "Wallet";
    final static String Bank_Money = "Bank";
   static  Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_frame);
        c = getApplicationContext();
        /*Fragments initialization*/
        Class Tabs = Tabs.class;

        try{
            tabs = (Fragment)Tabs.newInstance();

        }catch(Exception e){
            e.printStackTrace();
        }
        preferences = getSharedPreferences("money",Context.MODE_PRIVATE);
        editor = preferences.edit();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, tabs).commit();
    }

    /**Here we define medthods to update the wallet and bank account money,using shared preferences**/

    public static int getWalletAmount(){
        return preferences.getInt(Wallet_Money,0);

    }
    public static void add_Wallet_Money(int add){
        editor.putInt(Wallet_Money,getWalletAmount()+add);
        editor.commit();
    }
    public static void deduct_Wallet_Money(int deduct){
        if(getWalletAmount()-deduct>0) {
            editor.putInt(Wallet_Money, getWalletAmount() - deduct);
            editor.commit();
        }else{
            Toast.makeText(c,"You cannot spend more money than you have",Toast.LENGTH_SHORT).show();
        }
    }
    public static int getBankAmount(){
        return preferences.getInt(Bank_Money,0);

    }
    public static void add_Bank_Money(int add){
        editor.putInt(Bank_Money,getBankAmount()+add);
        editor.commit();

    }
    public static void deduct_Bank_Money(int deduct){
        if(getWalletAmount()-deduct>0) {
            editor.putInt(Bank_Money, getBankAmount() - deduct);
            editor.commit();
        }else{
            Toast.makeText(c,"You cannot deduct more money from bank than you have",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
