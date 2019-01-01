package com.atulvinod.moneytrack;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.Room.databaseBuilder;
@Database(entities = Lending_Entity.class,version = 1,exportSchema = false)
public abstract class Lending_Database extends RoomDatabase {

    public abstract Lending_DAO mEntityDAO();

    private static Lending_Database INSTANCE;

    static Lending_Database getDatabase(final Context c) {

        if(INSTANCE==null){
            synchronized (Database.class){
                if(INSTANCE==null){
                   INSTANCE = Room.databaseBuilder(c,Lending_Database.class,"lending_table").fallbackToDestructiveMigration().addCallback(callback).build();

                    /* .fallbackToDestructiveMigration clears the database when schema of the table is changed*/
                }
            }
        }

        return INSTANCE;
    }
    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){

        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}
