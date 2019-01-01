package com.atulvinod.moneytrack;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class All_Records extends AppCompatActivity {

    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__records);
        list = findViewById(R.id.list);
        Transaction_ViewModel viewmodel = ViewModelProviders.of(this).get(Transaction_ViewModel.class);
        final All_Transactions_Adapter adapter = new All_Transactions_Adapter(getApplicationContext(),viewmodel);

        viewmodel.getAllRecords().observe(this, new Observer<List<Transaction_Record>>() {
            @Override
            public void onChanged(@Nullable List<Transaction_Record> transaction_records) {
                adapter.setRecords(transaction_records);
            }
        });

        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(adapter);

    }
}
