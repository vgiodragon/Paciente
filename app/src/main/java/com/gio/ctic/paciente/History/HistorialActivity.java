package com.gio.ctic.paciente.History;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.gio.ctic.paciente.ConexionServer;
import com.gio.ctic.paciente.Otros.Paciente;
import com.gio.ctic.paciente.R;

import java.io.IOException;
import java.util.ArrayList;

public class HistorialActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView bien;

    private static String LOG_TAG = "CardViewActivity";
    private RecyclerView mRecyclerView;
    private HistorialAdapter mHAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayAdapter<CharSequence> adapterS;
    Paciente paciente;
    ArrayList <Historial>results;
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

        bien= (TextView) findViewById(R.id.tbien);
        Intent i = getIntent();
        paciente = i.getParcelableExtra("pac_login");
        bien.setText("Bienvenido " + paciente.getNombre());

        new cargarhistoria("/conHistory/",paciente.getId()).execute();
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


    private class cargarhistoria extends AsyncTask<String, Void, String> {
        String url;
        public cargarhistoria (String url,String iduser){
            this.url=url+iduser;
        }

        @Override
        protected String doInBackground(String... urls) {
            String respues="...";
            try {

                ConexionServer cs= new ConexionServer();
                Log.d("resultad",url + paciente.getId());
                results = cs.receiveJson(url);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return respues;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("respuesta", result);
            actualizaArray();
        }
    }

    private void actualizaArray(){
        mHAdapter.setmHistorialSet(results);
    }

    private ArrayList<Historial> getDataSet() {
        results = new ArrayList<>();
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
