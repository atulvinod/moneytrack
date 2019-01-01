package com.atulvinod.moneytrack;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.os.AsyncTask;

import java.util.List;

public class Lending_Repository {


    private Lending_DAO dao;
    private LiveData<List<Lending_Entity>> allEntities;

    Lending_Repository(Application app){
        Lending_Database db = Lending_Database.getDatabase(app);
        dao = db.mEntityDAO();
        allEntities = dao.getEntity();
    }
    LiveData<List<Lending_Entity>> getAllEntities(){return allEntities;}

    public void insert(Lending_Entity e){
        new insertTask(dao).execute(e);
    }
    public void delete(int ID){new deleteTask(dao).execute(ID);}
    public void update(Lending_EntityData e){ new updateTask(dao).execute(e);}

    private static class deleteTask extends AsyncTask<Integer,Void,Void> {
        private Lending_DAO d;
        deleteTask(Lending_DAO dao){
            this.d = dao;
        }

        @Override
        protected Void doInBackground(Integer... strings) {
            d.deleteRow(strings[0]);
            return null;
        }
    }

    private static class insertTask extends AsyncTask<Lending_Entity,Void,Void>{
        private Lending_DAO d;

        @Override
        protected Void doInBackground(Lending_Entity... entities) {
            d.insert(entities[0]);
            return null;
        }

        insertTask(Lending_DAO edao){
            d = edao;
        }
    }
    private static class updateTask extends AsyncTask<Lending_EntityData,Void,Void>{
        private Lending_DAO d;
        updateTask(Lending_DAO dao){
            d =dao;
        }
        @Override
        protected Void doInBackground(Lending_EntityData... integers) {
            d.update(integers[0].getID(),integers[0].getAmount());
            return null;
        }
    }

}
