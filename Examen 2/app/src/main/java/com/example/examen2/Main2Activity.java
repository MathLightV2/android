package com.example.examen2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class Main2Activity extends AppCompatActivity {
    int etapes = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // VIEWS
        ImageView montgolfiere = findViewById(R.id.imageView);
        EditText champ = findViewById(R.id.editTextText);
        Button bouton = findViewById(R.id.button);

        // ANIMATORS
        ObjectAnimator anim1x = ObjectAnimator.ofFloat(montgolfiere, "translationX", 400);
        ObjectAnimator anim1y = ObjectAnimator.ofFloat(montgolfiere, "translationY", -500);

        AnimatorSet anim1 = new AnimatorSet();
        anim1.setDuration(2000);
        anim1.playTogether(anim1x, anim1y);

        ObjectAnimator anim2x = ObjectAnimator.ofFloat(montgolfiere, "translationX", 0);
        ObjectAnimator anim2y = ObjectAnimator.ofFloat(montgolfiere, "translationY", -1000);

        AnimatorSet anim2 = new AnimatorSet();
        anim2.setDuration(2000);
        anim2.playTogether(anim2x, anim2y);

        ObjectAnimator anim3 = ObjectAnimator.ofFloat(montgolfiere, "translationY", -1300);
        anim3.setDuration(2000);

        ObjectAnimator animFinScaleX = ObjectAnimator.ofFloat(montgolfiere, "scaleX", 50);
        ObjectAnimator animFinScaleY = ObjectAnimator.ofFloat(montgolfiere, "scaleY", 50);
        ObjectAnimator animFinAlpha = ObjectAnimator.ofFloat(montgolfiere, "Alpha", 0);

        AnimatorSet animFin = new AnimatorSet();
        animFin.playTogether(animFinScaleX, animFinScaleY, animFinAlpha);

        AnimatorSet animFinal = new AnimatorSet();
        animFinal.setDuration(2000);
        animFinal.playSequentially(anim3, animFin);


        // JSON
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.jsonbin.io/v3/b/673cf708acd3cb34a8ab4e3c?meta=false";
        Vector<String> albums = new Vector<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray items = response.getJSONArray("items");

                        for (int i = 0; i < items.length(); i++){
                            JSONObject item = items.optJSONObject(i);
                            albums.add(item.getString("name"));
                        }
                        System.out.println(albums);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }, Throwable::printStackTrace);

        queue.add(jsonObjectRequest);

        Vector<String> history = new Vector<>();

        // ONCLICK
        bouton.setOnClickListener(source -> {
            String userInput = champ.getText().toString();

            // 1
            if (etapes == 1){
                if (albums.contains(userInput)){
                    anim1.start();
                    etapes++;
                    history.add(userInput);
                    champ.setText("");
                }
                else {
                    Toast.makeText(Main2Activity.this, "Mauvaise réponse", Toast.LENGTH_LONG).show();
                }
            }

            // 2
            else if (etapes == 2 && !anim1.isRunning()){
                if (albums.contains(userInput)){
                    if (!history.contains(userInput)){
                        anim2.start();
                        etapes++;
                        history.add(userInput);
                        champ.setText("");
                    }
                    else {
                        Toast.makeText(Main2Activity.this, "Réponse déjà donnée !", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Main2Activity.this, "Mauvaise réponse", Toast.LENGTH_LONG).show();
                }
            }

            // 3
            else if (etapes == 3 && !anim2.isRunning()) {
                if (albums.contains(userInput)) {
                    if (!history.contains(userInput)) {
                        animFinal.start();
                        etapes++;
                        champ.setText("");
                        bouton.setEnabled(false);
                        bouton.setText("Félicitations !");
                    } else {
                        Toast.makeText(Main2Activity.this, "Réponse déjà donnée !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Main2Activity.this, "Mauvaise réponse", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}