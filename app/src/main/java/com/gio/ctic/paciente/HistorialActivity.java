package com.gio.ctic.paciente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

public class HistorialActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private static String LOG_TAG = "CardViewActivity";
    private RecyclerView mRecyclerView;
    private HistorialAdapter mHAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayAdapter<CharSequence> adapterS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historiales);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mHAdapter = new HistorialAdapter(getDataSet());
        mRecyclerView.setAdapter(mHAdapter);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerOrdenar);
        spinner.setOnItemSelectedListener(this);
        adapterS = ArrayAdapter.createFromResource(this,
                R.array.burra_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapterS);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ( mHAdapter).setOnItemClickListener(new HistorialAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });

    }


    private ArrayList<Historial> getDataSet() {
        ArrayList results = new ArrayList<>();
        results.add(new Historial("11/02/2016","Dr. AGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("10/02/2016","Dr. BGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("09/02/2016","Dr. CGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("08/02/2016","Dr. DGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("07/02/2016","Dr. EGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("06/02/2016","Dr. FGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("15/02/2016","Dr. GGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("14/02/2016","Dr. HGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("13/02/2016","Dr. IGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("12/02/2016","Dr. JGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("01/02/2016","Dr. KGrey","Medicina General","Cold, Heart breaking","Lupus"));
        results.add(new Historial("02/02/2016","Dr. LGrey","Medicina General","Cold, Heart breaking","Lupus"));

        return results;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mHAdapter.Ordenar(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
