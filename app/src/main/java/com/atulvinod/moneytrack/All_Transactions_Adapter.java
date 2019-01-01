package com.atulvinod.moneytrack;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;

import java.util.List;

public class All_Transactions_Adapter extends RecyclerView.Adapter<All_Transactions_Adapter.View_Holder>{
    private List<Transaction_Record> records;

    LayoutInflater inflater;
    Context context;
    RapidFloatingActionButton btn;
    RapidFloatingActionLayout layout;
    int items;
    boolean activity_origin_set = false;
    Transaction_ViewModel mViewModel;
    IndiaCurrencyFormatter formatter;


    public All_Transactions_Adapter(Context context,RapidFloatingActionButton btn,RapidFloatingActionLayout layout,Transaction_ViewModel model){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.btn = btn;
        this.layout = layout;
        this.mViewModel = model;
        formatter = new IndiaCurrencyFormatter();


    }
    public All_Transactions_Adapter(Context c,Transaction_ViewModel model){
        this.context = context;
        inflater = LayoutInflater.from(c);
        this.mViewModel = model;
        formatter = new IndiaCurrencyFormatter();
    }


    class View_Holder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView Discription,Amount,Date,Transffered_To;


        public View_Holder(View v){
            super(v);
            Discription = v.findViewById(R.id.discription_text);
            Amount = v.findViewById(R.id.recent_amount);
            Date = v.findViewById(R.id.recent_date);
            Transffered_To  =v.findViewById(R.id.recent_indicator);
            image = v.findViewById(R.id.transaction_image);

        }

    }






    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recent_transaction,parent,false);

        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        Transaction_Record r = records.get(position);
        if(r.getTransaction_flags().equals(Modifiers.FLAG_WALLET_ADD)){
            holder.image.setImageResource(R.drawable.walleticon);
            holder.Transffered_To.setText("Wallet");
        }else{
            holder.image.setImageResource(R.drawable.money_box_icon);
            holder.Transffered_To.setText("Bank");
        }
        holder.Amount.setText(formatter.formatAmount(r.getAmount()));
        holder.Date.setText(r.getDay()+"/"+r.getMonth()+"/"+r.getYear());
        holder.Discription.setText(r.getDiscription());

    }

    @Override
    public int getItemCount() {

        if(records==null){
            return 0;
        }else{
           return records.size();
        }
    }



    public void setRecords(List<Transaction_Record> r){
        records = r;
        notifyDataSetChanged();
    }




}
