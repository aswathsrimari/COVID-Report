/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoronaActivity extends AppCompatActivity {

    public static final String LOG_TAG = CoronaActivity.class.getName();


    final ArrayList<CoronaDetails> corona = new ArrayList<>();

    // Create a fake list of earthquake locations.
    String url = "https://api.covid19india.org/state_district_wise.json";

    RequestQueue requestQueue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.corona_activity);

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //JSONArray key = response.names();


                        try{
                                JSONObject Ob = response.getJSONObject("Tamil Nadu");
                                    JSONObject a;
                                    a = Ob.getJSONObject("districtData");
                                    JSONArray B = a.names();
                                for (int j = 0; j < B.length(); j++) {
                                        String Dis;
                                        Dis = B.getString(j);
                                        Log.i("FUCk", Dis);
                                        JSONObject G;
                                        G = a.getJSONObject(Dis);
                                        corona.add(new CoronaDetails(Dis, G.getLong("confirmed")));
                                    }
                                    DisplayToView();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                    } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);



        // Find a reference to the {@link ListView} in the layout

    }

    public void DisplayToView(){

        ListView coronaListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        CoronaAdapter adapter = new CoronaAdapter (this, corona);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        coronaListView.setAdapter(adapter);
    }
}
