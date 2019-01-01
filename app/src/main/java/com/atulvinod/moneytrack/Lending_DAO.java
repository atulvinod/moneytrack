package com.atulvinod.moneytrack;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface Lending_DAO {
    @Query("SELECT * FROM lending_table ORDER BY mID ASC")
    LiveData<List<Lending_Entity>> getEntity();

    @Insert
    void insert(Lending_Entity entity);

    @Query("DELETE FROM lending_table")
    void deleteAll();

    @Query("DELETE FROM lending_table WHERE mID = :id")
    void deleteRow(int id);

    @Query("UPDATE lending_table SET amount =:value WHERE mID = :id")
    void update(int id,int value);
}
