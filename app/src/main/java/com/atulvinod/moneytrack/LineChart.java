package com.atulvinod.moneytrack;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LineChart extends AppCompatActivity implements LineChartFragment.OnFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
