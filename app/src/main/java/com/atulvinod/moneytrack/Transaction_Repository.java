package com.atulvinod.moneytrack;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.time.Month;
import java.util.List;

public class Transaction_Repository {
    private Transaction_DAO dao;
    String Day = " ";
    String Month=" ";
    String Type=" ";
    String Flag=" ";
    Application App;
    Transaction_Repository repository;
    public Transaction_Repository(Application app){
        Transaction_Database db = Transaction_Database.getDatabase(app.getApplicationContext());
        dao = db.mTransaction_dao();
        this.App = app;

    }

    public Transaction_Repository getRepository(){
        new getRepository().execute();
        return repository;
    }
    class getRepository extends AsyncTask<Application,Void,Transaction_Repository>{
        @Override
        protected Transaction_Repository doInBackground(Application... voids) {
            repository = new Transaction_Repository(voids[0]);
            return null;


        }
    }

    public LiveData<List<Transaction_Record>> getAllRecords(){
        return dao.Live_getAllRecords();
    }
    public void insert(Transaction_Record record){
        new insertTask().execute(record);
    }
    class insertTask extends AsyncTask<Transaction_Record,Void,Void>{
        @Override
        protected Void doInBackground(Transaction_Record... transaction_records) {
             dao.insert(transaction_records[0]);
             return null;
        }
    }

}
