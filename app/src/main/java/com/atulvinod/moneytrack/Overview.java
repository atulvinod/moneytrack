package com.atulvinod.moneytrack;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Overview.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Overview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Overview extends Fragment  implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Overview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Overview.
     */
    public static Overview newInstance(String param1, String param2) {
        Overview fragment = new Overview();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    DialogPlus add_expense;
    DialogPlus add_money;
    Button allRecents;
    TextView wallet_money,bank_money;
    Transaction_ViewModel mTransaction_viewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Pre requisite initialization
        final IndiaCurrencyFormatter formatter = new IndiaCurrencyFormatter();
        mTransaction_viewModel = ViewModelProviders.of(this).get(Transaction_ViewModel.class);
        final Calendar calendar = Calendar.getInstance();



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview, container,false);
      wallet_money = view.findViewById(R.id.wallet_money);
       bank_money = view.findViewById(R.id.bank_money);

        /*Code to implement the FAB*/
        RapidFloatingActionButton btn = view.findViewById(R.id.activity_main_rfab);
        RapidFloatingActionLayout layout = view.findViewById(R.id.activity_main_rfal);

        RapidFloatingActionContentLabelList fab = new RapidFloatingActionContentLabelList(getContext());
        List<RFACLabelItem> items = new ArrayList<>();
        /*Adding FAB Contents*/
        items.add(new RFACLabelItem<Integer>().setResId(R.drawable.rfab__drawable_rfab_default).setLabel("Add Expense").setIconNormalColor(Color.parseColor("#264e70")));
        items.add(new RFACLabelItem<Integer>().setResId(R.drawable.rfab__drawable_rfab_default).setLabel("Add money to Wallet/Bank").setIconNormalColor(Color.parseColor("#264e70")));
        fab.setItems(items);
        fab.setOnRapidFloatingActionContentLabelListListener(this);
        RapidFloatingActionHelper helper = new RapidFloatingActionHelper(getContext(),layout,btn,fab).build();


        wallet_money.setText(formatter.formatAmount(MainActivity.getWalletAmount()));
        bank_money.setText(formatter.formatAmount(MainActivity.getBankAmount()));

        /*Creating dialogs*/
       final DialogPlusBuilder AddExpenseDialogBuilder = DialogPlus.newDialog(getContext()).setGravity(Gravity.BOTTOM).setContentHolder(new ViewHolder(R.layout.dialog_add_expense)).setExpanded(true).setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
       final DialogPlusBuilder AddMoneyDialogBuilder = DialogPlus.newDialog(getContext()).setGravity(Gravity.BOTTOM).setContentHolder(new ViewHolder(R.layout.dialog_add_money)).setExpanded(true).setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT);


        add_expense = AddExpenseDialogBuilder.create();
        add_money = AddMoneyDialogBuilder.create();


       final View add_expense_dialog_view = add_expense.getHolderView();
       final View add_money_dialog_view  = add_money.getHolderView();

       /*Spinner adapter and setting onItemSelectedListener of Add expense dialog*/
        Spinner type_spinner = add_expense_dialog_view.findViewById(R.id.type_spinner);
        ArrayAdapter<CharSequence> type_spinner_adapter = ArrayAdapter.createFromResource(getContext(),R.array.types_array,R.layout.support_simple_spinner_dropdown_item);
        type_spinner_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        type_spinner.setAdapter(type_spinner_adapter);
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SELECTED",(String)parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Setting up the save fabs and adding on click listeners*/
        FloatingActionButton save_expense = add_expense_dialog_view.findViewById(R.id.save_expense);
        save_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_expense.dismiss();
            }
        });
        FloatingActionButton save_money = add_money_dialog_view.findViewById(R.id.save_add_money);
        save_money.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText amount_input = add_money_dialog_view.findViewById(R.id.amount_input);
                if(!amount_input.getText().equals("")){
                    int amount = Integer.parseInt(amount_input.getText().toString());
                    final RadioGroup flag_group = add_money_dialog_view.findViewById(R.id.flag_group);
                    RadioButton flag = add_money_dialog_view.findViewById(flag_group.getCheckedRadioButtonId());
                    String flagSet = "";
                    //switch case to determine the flag type
                    switch (flag.getText().toString()) {
                        case "Wallet":
                            flagSet = Modifiers.FLAG_WALLET_ADD;
                            MainActivity.add_Wallet_Money(amount);

                            wallet_money.setText(formatter.formatAmount(MainActivity.getWalletAmount()));
                            break;
                        case "Bank":
                            MainActivity.add_Bank_Money(amount);
                            bank_money.setText(formatter.formatAmount(MainActivity.getBankAmount()));
                            flagSet = Modifiers.FLAG_BANK_ADD;
                            break;
                    }
                    TextView discription_view = add_money_dialog_view.findViewById(R.id.discription_input);

                    get_datetime dateTime = new get_datetime();
                    mTransaction_viewModel.insert_record(new Transaction_Record(amount, dateTime.getDayOfMonth(), dateTime.getMonth(), dateTime.getYear(), Modifiers.NOT_A_TYPE_TRANSACTION, flagSet, get_datetime.getTime(), discription_view.getText().toString(), 0));
                    Toast.makeText(getContext(),"Added "+formatter.formatAmount(amount)+" to "+flag.getText().toString(),Toast.LENGTH_SHORT).show();

                    add_money.dismiss();
                } else{
                    Toast.makeText(getContext(),"Fill amount first",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Setting up recent transactions*/
        final RecyclerView recent_transaction = view.findViewById(R.id.recent_transactions);
        final Recent_Transactions_Adapter recent_adapter = new Recent_Transactions_Adapter(getContext(),btn,layout,mTransaction_viewModel);

        recent_transaction.setLayoutManager(new LinearLayoutManager(getContext()));
        recent_transaction.setAdapter(recent_adapter);
        recent_transaction.addOnScrollListener(new Recents_Scroll_Detect(layout,btn));
        mTransaction_viewModel.getAllRecords().observe(this, new Observer<List<Transaction_Record>>() {
            @Override
            public void onChanged(@Nullable List<Transaction_Record> transaction_records) {
                recent_adapter.setRecords(transaction_records);
            }
        });

        /*Setting up all recents*/
        allRecents = view.findViewById(R.id.see_all_transactions);
        allRecents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),All_Records.class);
                startActivity(i);
            }
        });


        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
      switch(position){
          case 0:
              add_expense.show();
              break;
          case 1:
              add_money.show();
              break;
      }
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        switch(position){
            case 0:
                add_expense.show();

            case 1:
                add_money.show();
                break;
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
