package com.gio.ctic.paciente;

import android.util.Log;

import com.gio.ctic.paciente.History.Historial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by giovanny on 09/06/16.
 */
public class ConexionServer {
    String urlp="http://52.40.252.10:8081/";

    public String LoginPac(String func,String user,String pass) throws IOException {
        InputStream is = null;
        int len = 100;

        try {
            String furl=urlp+func+(user+"@!"+pass).replace(" ","__");
            URL url = new URL(furl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public ArrayList<Historial> receiveJson(String func)throws IOException {

        InputStream is = null;
        int len = 1000;
        ArrayList<Historial> historials=new ArrayList<>();

        try {
            String furl=urlp+func;
            URL url = new URL(furl);
            Log.d("respuesta", furl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // Starts the query
            conn.connect();


            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            Log.d("respuesta", contentAsString);
            JSONObject jsonObject = new JSONObject(contentAsString);
            JSONArray jsonArray = jsonObject.getJSONArray("array_json");

            Log.d("respuesta", "Tamano json: " + jsonArray.length());

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject json_data = jsonArray.getJSONObject(i);
                Historial paci= new Historial(json_data.getString("id_pac"),
                        json_data.getString("id_doc"),
                        json_data.getString("fecha") ,
                        json_data.getString("sintomas") ,
                        json_data.getString("descripcion") );
                historials.add(paci);
            }

            //return contentAsString;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return historials;
    }

    private String readIt(InputStream stream, int len) throws IOException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
