package com.example.examen2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Button boutonAnim;
    ObjectAnimator animation;
    ObjectAnimator animation2;

    ObjectAnimator animation3;
    ObjectAnimator animation4;
    ObjectAnimator animation5;
    ObjectAnimator animation6;
    List<ObjectAnimator > listeAnimation;

    Vector <String> v;
    EditText reponse;
    boolean bonneRep;

    View object;

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

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.jsonbin.io/v3/b/673cf708acd3cb34a8ab4e3c?meta=false";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    JSONArray tab = null;
                    try {
                    tab = (JSONArray)response.get("total_tracks");
                        for (int i = 0; i < tab.length(); i++) {
                            JSONObject temp = tab.getJSONObject(i);
                            v.add(temp.getString("nom"));
                        }
                    }catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } , Throwable::printStackTrace);






//          question 1
        AnimatorSet setUn = new AnimatorSet();

        reponse = findViewById(R.id.editTextText);

        boutonAnim = findViewById(R.id.buttonAnim);
        object = findViewById(R.id.mongo);

        animation = ObjectAnimator.ofFloat(object,View.TRANSLATION_Y, -350);
        animation2 = ObjectAnimator.ofFloat(object,View.TRANSLATION_X, 350);
        setUn.playTogether(animation, animation2);
        setUn.setDuration(2000);

        //boutonAnim.setOnClickListener(source -> setUn.start() );

        // question 2

        animation3 = ObjectAnimator.ofFloat(object,View.TRANSLATION_Y, -350);
        animation4 = ObjectAnimator.ofFloat(object,View.TRANSLATION_X, -350);


        animation5 = ObjectAnimator.ofFloat(object,View.TRANSLATION_Y, -350);
        animation6 = ObjectAnimator.ofFloat(object,View.TRANSLATION_X, 350);









        //listeAnimation.add(animation); plente

        bonneRep = false;
        boutonAnim.setOnClickListener(source -> {
            for (int x = 0; x < v.size(); ++x) {
                if (reponse.getText().toString() == v.get(x)) {
                    bonneRep = true;
                     setUn.start();

                }
                if (bonneRep) {
                    Toast.makeText(MainActivity.this, "mauvaise réponse", Toast.LENGTH_LONG).show();
                }
            }
        });





    }
}