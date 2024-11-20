package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class PlaylistActivity extends AppCompatActivity implements Observer{
    Modele modele;
    ListView list;
    Vector<Hashtable<String, Object>> vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_playlist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        modele = new Modele(this);
        modele.addObserver(this);
    }

    @Override
    public void changement(List<Music> musiques) {
        Hashtable<String, Object> temp;
        vector = new Vector<>();

        for (Music music : musiques) {
            temp = new Hashtable<>();
            temp.put("nom", music.getTitle());
            temp.put("artiste", music.getArtist());
            temp.put("album", music.getAlbum());
            temp.put("image", music.getImage());
            vector.add(temp);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, vector, R.layout.pochette,
                new String[]{"nom", "artiste", "album", "image"},
                new int[]{ R.id.nom, R.id.artiste, R.id.album, R.id.imageView });

        // Pour mettre l'image url en image dans le ImageView
        adapter.setViewBinder((view, data, textRepresentation) -> {
            if (view.getId() == R.id.imageView){
                Glide.with(getApplicationContext()).load(data).into((ImageView)view);
                return true;
            }
            return false;
        });

        list.setAdapter(adapter);
        list.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent i = new Intent(PlaylistActivity.this, PlayerActivity.class);
            i.putExtra("position", position);
            startActivity(i);
        });
    }
}