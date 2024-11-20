package com.example.tp1;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Modele implements Sujet{
    private Context context;
    private List<Music> musiques;
    private Observer obs;

    public Modele(Context context){
        this.context = context;
        requestQueue();
        Vector v = new Vector()
    }

    private void requestQueue() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://api.jsonbin.io/v3/b/6723b430e41b4d34e44bfa92?meta=false";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Gson gson = new Gson();
                    ListeMusiques listeMusiques = gson.fromJson(response, ListeMusiques.class);

                    musiques = listeMusiques.getMusic();
                    avertirObservateurs();

                }, Throwable::printStackTrace);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void addObserver(Observer obs) {
        this.obs = obs;
    }

    @Override
    public void removeObserver(Observer obs) {
        obs = null;
    }

    @Override
    public void avertirObservateurs() {
        obs.changement(musiques);
    }

    private class ListeMusiques{
        private List<Music> music;
        public ListeMusiques(List<Music> music){this.music = music;}
        public List<Music> getMusic() {return music;}
    }

}