package com.example.apiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String URL = "http://192.168.43.30/apiAndroid";
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.Result);

//        read JSON
        JsonObjectRequest data = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String x = response.toString();
                try {
                    JSONObject object = new JSONObject(x);
                    JSONArray result = object.getJSONArray("Result");
                    int rowData = result.length();

                    String show;
                    show = "";
                    for (int i = 0; i < rowData; i++) {
                        JSONObject dataResult = result.getJSONObject(i);
                        String kode = dataResult.getString("kode_barang");
                        String nama = dataResult.getString("nama_barang");
                        String satuan = dataResult.getString("satuan");
                        Integer hbeli = dataResult.getInt("hbeli");
                        Integer hjual = dataResult.getInt("hjual");
                        Integer diskon = dataResult.getInt("diskon");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError error) {

                }
            }
        });



    }
}
