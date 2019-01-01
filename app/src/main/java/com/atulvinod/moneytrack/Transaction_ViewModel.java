package com.atulvinod.moneytrack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public  class Transaction_ViewModel extends AndroidViewModel {
    private Transaction_Repository repo;
    private LiveData<List<Transaction_Record>> getAllRecords;
    private LiveData<List<Transaction_Record>> getRecordsViaFlag;

    public Transaction_ViewModel(Application app){
        super(app);
        repo = new Transaction_Repository(app);

    }
    public LiveData<List<Transaction_Record>> getAllRecords(){
        return repo.getAllRecords();
    }
    public void insert_record(Transaction_Record record){
        repo.insert(record);
    }
}
