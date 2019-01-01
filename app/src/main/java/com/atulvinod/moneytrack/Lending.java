package com.atulvinod.moneytrack;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.listener.OnRapidFloatingButtonSeparateListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Lending.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Lending#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lending extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Lending() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Lending.
     */
    public static Lending newInstance(String param1, String param2) {
        Lending fragment = new Lending();
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

    DialogPlusBuilder add_dialog_builder;
    DialogPlus add_dialog;
    EditText name,amount;
    FloatingActionButton add_record_button;
    Lending_ViewModel LENDING_VIEWMODEL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lending, container, false);

        //Fetching the view model
        LENDING_VIEWMODEL = ViewModelProviders.of(this).get(Lending_ViewModel.class);


        /*Implementing the recycler view*/
        RecyclerView list = view.findViewById(R.id.lending_recycler_view);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        final Lending_ListAdapter adapter = new Lending_ListAdapter(getContext(),LENDING_VIEWMODEL);
        list.setAdapter(adapter);
      LENDING_VIEWMODEL.getAllWords().observe(this, new Observer<List<Lending_Entity>>() {
            @Override
            public void onChanged(@Nullable List<Lending_Entity> lending_entities) {
                adapter.setElements(lending_entities);
            }
        });

        /*Setting up the dialog box to get the record*/
        add_dialog_builder = DialogPlus.newDialog(getContext()).setGravity(Gravity.BOTTOM).setContentHolder(new ViewHolder(R.layout.dialog_add_lend)).setExpanded(true);
        add_dialog = add_dialog_builder.create();
        final View dialog_view = add_dialog.getHolderView();
        name = dialog_view.findViewById(R.id.lend_record_name);
        amount = dialog_view.findViewById(R.id.lend_record_amount);

        //button inside the dialog box to save the entry
        add_record_button = dialog_view.findViewById(R.id.floatingActionButton_add_record);


        /*Setting up RadioButtons*/
        final RadioGroup ownership_group = dialog_view.findViewById(R.id.ownership_radios);

        /*Setting up FABs*/
        RapidFloatingActionButton add_button = view.findViewById(R.id.add_lend_record);
        add_button.setOnRapidFloatingButtonSeparateListener(new OnRapidFloatingButtonSeparateListener() {
            @Override
            public void onRFABClick() {
                add_dialog.show();
            }
        });

        add_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*First checks if all the feilds arent empty*/
                if (!amount.getText().toString().equals("") && !name.getText().toString().equals("") && !amount.getText().toString().equals(" ") && !name.getText().toString().equals(" ")) {
                    int _amount = Integer.parseInt(amount.getText().toString());
                    String _name = name.getText().toString();
                    RadioButton button = dialog_view.findViewById(ownership_group.getCheckedRadioButtonId());
                    String ownership;
                    if (button.getText().equals("They owe you")) {
                        ownership = "THEY_OWE_YOU";
                    } else {
                        ownership = "YOU_OWE_THEM";
                    }

                    add_dialog.dismiss();
                    LENDING_VIEWMODEL.insert(new Lending_Entity(_amount, _name, ownership, 0));
                } else {
                    Toast.makeText(getContext(), "Fill all the fields ", Toast.LENGTH_SHORT).show();
                }
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
