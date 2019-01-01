package com.atulvinod.moneytrack;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(version = 1,entities = Transaction_Record.class,exportSchema = false)
public abstract class Transaction_Database extends RoomDatabase {
    public abstract Transaction_DAO mTransaction_dao();
    public static Transaction_Database INSTANCE;
    static Transaction_Database getDatabase(final Context c){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(c,Transaction_Database.class,"transaction_database").addCallback(callback).fallbackToDestructiveMigration().build();

        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){

        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}
