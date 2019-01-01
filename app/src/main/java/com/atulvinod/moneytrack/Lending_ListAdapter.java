package com.atulvinod.moneytrack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.List;

public class Lending_ListAdapter extends RecyclerView.Adapter<Lending_ListAdapter.LendingViewHolder> {

    /**All the arithmetic operations are to be done on the internalAmount which isn't formatted by the bindViewHolder**/

    LayoutInflater inflater;
    Context CONTEXT;
    IndiaCurrencyFormatter formatter;
    List<Lending_Entity> entities;
    Lending_ViewModel view_model;

    public Lending_ListAdapter(Context c,Lending_ViewModel m) {
        CONTEXT = c;
        formatter = new IndiaCurrencyFormatter();
        inflater = LayoutInflater.from(CONTEXT);
        view_model = m;
    }
    void setElements(List<Lending_Entity> elements){
        entities = elements;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.lending_list_item_layout,parent,false);
        return new LendingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LendingViewHolder holder, int position) {
        Lending_Entity entity = entities.get(position);

        holder.name.setText(entity.getName());
        holder.amount.setText(formatter.formatAmount(entity.getAmount()));
        holder.setInternalAmount(""+entity.getAmount());
        holder.setID(entity.getID());
        if(entity.getOwnership().equals("YOU_OWE_THEM")){
            holder.ownership.setText("You owe them");
        }else{
            holder.ownership.setText("They owe you");
        }


    }

    @Override
    public int getItemCount() {

        if(entities != null){
            return entities.size();
        }else{
            return 0;
        }
    }

    class LendingViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView amount;
        TextView ownership;
        int ID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        Button update,delete;
        private String internalAmount;

        public String getInternalAmount() {
            return internalAmount;
        }

        public void setInternalAmount(String internalAmount) {
            this.internalAmount = internalAmount;
        }

        public LendingViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.lend_name);
            amount = v.findViewById(R.id.lend_amount);
            update = v.findViewById(R.id.update_button);
            delete = v.findViewById(R.id.delete_button);
            ownership = v.findViewById(R.id.party_indicator);


            /*Setting on click listeners*/
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Creating dialog box*/
                    DialogPlusBuilder updateDialogBuilder = DialogPlus.newDialog(CONTEXT).setGravity(Gravity.BOTTOM).setContentHolder(new ViewHolder(R.layout.dialog_update_lend)).setExpanded(true).setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    final DialogPlus updateDialog = updateDialogBuilder.create();
                    View updateView = updateDialog.getHolderView();

                    updateDialog.show();

                    /*Creating buttons*/
                    Button add = updateView.findViewById(R.id.update_add);
                    Button deduct = updateView.findViewById(R.id.update_deduct);

                    /*Getting data from Edit Text*/
                    final EditText Updateamount = updateView.findViewById(R.id.update_record_amount);
                    TextView amount_indicator = updateView.findViewById(R.id.amount_indicator);
                    amount_indicator.setText(amount.getText());


                    /**Setting on click listeners on update buttons**/

                    add.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            if(!Updateamount.equals("")||!Updateamount.equals(" ")) {
                                view_model.update(new Lending_EntityData(getID(), Integer.parseInt(getInternalAmount()) + Integer.parseInt(Updateamount.getText().toString())));
                                Toast.makeText(CONTEXT,"Money added\nPrevious balance: "+formatter.formatAmount(Integer.parseInt(getInternalAmount()))+"\nCurrent balance: "+formatter.formatAmount(Integer.parseInt(getInternalAmount()) + Integer.parseInt(Updateamount.getText().toString())),Toast.LENGTH_SHORT).show();

                                updateDialog.dismiss();

                            }else{
                                Toast.makeText(CONTEXT,"Amount Field is Empty",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    deduct.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!Updateamount.equals("")||!Updateamount.equals(" ")) {
                                if(Integer.parseInt(Updateamount.getText().toString())>Integer.parseInt(getInternalAmount())){
                                    Toast.makeText(CONTEXT,"You cannot deduct more money than you've lent",Toast.LENGTH_SHORT).show();

                                }else {
                                    view_model.update(new Lending_EntityData(getID(), Integer.parseInt(getInternalAmount()) - Integer.parseInt(Updateamount.getText().toString())));
                                    Toast.makeText(CONTEXT,"Money deducted\nPrevious balance: "+formatter.formatAmount(Integer.parseInt(getInternalAmount()))+"\nCurrent balance: "+formatter.formatAmount(Integer.parseInt(getInternalAmount()) - Integer.parseInt(Updateamount.getText().toString())),Toast.LENGTH_SHORT).show();
                                    updateDialog.dismiss();
                                }
                            }else{
                                Toast.makeText(CONTEXT,"Amount Field is Empty",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }
            });
            delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /*Creating dialog*/
                    final DialogPlusBuilder deleteBuilder = DialogPlus.newDialog(CONTEXT).setContentHolder(new ViewHolder(R.layout.dialog_delete_lend)).setExpanded(true).setGravity(Gravity.BOTTOM);
                    final DialogPlus deleteDialog = deleteBuilder.create();
                    View view = deleteDialog.getHolderView();

                    deleteDialog.show();

                    /*Creating buttons*/
                    Button yes = view.findViewById(R.id.delete_yes);
                    Button no = view.findViewById(R.id.delete_no);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            view_model.delete(getID());
                            Toast.makeText(CONTEXT,"Record was deleted ",Toast.LENGTH_SHORT).show();
                            deleteDialog.dismiss();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteDialog.dismiss();
                        }
                    });
                }
            });
        }
    }


}
