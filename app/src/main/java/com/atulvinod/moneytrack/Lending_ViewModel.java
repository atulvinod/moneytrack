package com.atulvinod.moneytrack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class Lending_ViewModel extends AndroidViewModel {
    private Lending_Repository repo;
    private LiveData<List<Lending_Entity>> entities;

    public Lending_ViewModel(Application app){
        super(app);
        repo = new Lending_Repository(app);
        entities = repo.getAllEntities();
    }
    LiveData<List<Lending_Entity>> getAllWords(){
        return entities;
    }
    public void insert(Lending_Entity e){
        repo.insert(e);
    }
    public void delete(int id){
        repo.delete(id);
    }
    public void update(Lending_EntityData e){
        repo.update(e);
    }

}
