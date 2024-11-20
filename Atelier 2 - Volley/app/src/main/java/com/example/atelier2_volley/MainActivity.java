package com.example.atelier2_volley;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Produit produit = new Produit.Builder().setNom("test").setPrix(20.00).build();

        listView = findViewById(R.id.liste);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.jsonbin.io/v3/b/637056232b3499323bfe110a?meta=false";

        /* STRING REQUEST
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                }, Throwable::printStackTrace);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

         */



         //AVEC JSONOBJECTREQUEST
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    JSONArray tab = null;
                    try {
                        tab = (JSONArray)response.get("articles");
                        //JSONObject le_premier = tab.getJSONObject(0);
                        //String prix = (String)le_premier.get("prix");


                        List<Map<String, String>> data = new ArrayList<>();


                        for (int i = 0; i < tab.length(); i++) {
                            JSONObject temp = tab.getJSONObject(i);
                            Map<String, String> map = new HashMap<>();
                            map.put("nom", temp.getString("nom"));
                            map.put("prix", temp.getString("prix"));
                            data.add(map);
                        }

                        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.info,
                                                new String[]{"nom", "prix"},
                                                new int[]{ R.id.nom, R.id.prix});

                        listView.setAdapter(adapter);


                    }  catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }, Throwable::printStackTrace);

        queue.add(jsonObjectRequest);





        // AVEC GSON
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Gson gson = new Gson();
                    ListeProduits listeProduits = gson.fromJson(response, ListeProduits.class);
                    List<Produit> articles = listeProduits.getArticles();

                    List<Map<String, String>> data = new ArrayList<>();

                    for ( Produit produit : articles ){
                        System.out.println(produit.getNom() + " " + produit.getPrix());

                        Map<String, String> map = new HashMap<>();
                        map.put("nom", produit.getNom());
                        map.put("prix", String.valueOf(produit.getPrix()));
                        data.add(map);
                    }

                    SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.info,
                            new String[]{"nom", "prix"},
                            new int[]{ R.id.nom, R.id.prix});

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
                        Produit rep = articles.get(position);
                        String nom = rep.getNom();
                        Toast.makeText(MainActivity.this, nom, Toast.LENGTH_LONG).show();
                    });


                }, Throwable::printStackTrace);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public class ListeProduits{
        private List<Produit> articles;

        public ListeProduits(List<Produit> articles){
            this.articles = articles;
        }

        public List<Produit> getArticles() {
            return articles;
        }
    }
}

