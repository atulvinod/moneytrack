package com.atulvinod.moneytrack;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Transaction_DAO {
    @Query("SELECT SUM(amount) FROM transaction_table WHERE transaction_type=:type AND month=:month")
    public int getTotalViaMonthAndType(String type,String month);

    @Query("SELECT SUM(amount) FROM transaction_table WHERE month=:month")
    public int getTotalViaMonth(String month);

    @Query("SELECT * FROM transaction_table WHERE transaction_flags=:flag")
    public List<Transaction_Record> getRecordsViaFlag(String flag);

    @Query("SELECT SUM(amount) FROM transaction_table WHERE day=:day and month=:month")
    public int getTotalViaDayAndMonth(String day,String month);

    @Query("SELECT * FROM transaction_table ORDER BY ID DESC")
    public List<Transaction_Record> getAllRecords();


    /**Live data Lists**/


    @Query("SELECT * FROM transaction_table WHERE transaction_flags=:flag")
    public LiveData<List<Transaction_Record>> Live_getRecordsViaFlag(String flag);


    @Query("SELECT * FROM transaction_table ORDER BY ID DESC")
    public LiveData<List<Transaction_Record>> Live_getAllRecords();


    @Insert
    public void insert(Transaction_Record record);



}
