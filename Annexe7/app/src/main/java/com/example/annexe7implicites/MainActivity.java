package com.example.annexe7implicites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button boutonTel, boutonCarte, boutonPhoto;
    ActivityResultLauncher<Intent> lanceur;
    ImageView image;

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

        image = findViewById(R.id.imageView);

        lanceur = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), result ->{
            Bundle extras = result.getData().getExtras();
            Bitmap imageBitmnap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmnap);
        });

        boutonTel = findViewById(R.id.boutonAppel);
        boutonCarte = findViewById(R.id.boutonVille);
        boutonPhoto = findViewById(R.id.boutonPhoto);

        boutonTel.setOnClickListener(source -> {
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+5147571733") );
            startActivity(i);
        });

        boutonCarte.setOnClickListener(source -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Montreal, +qc, +ca "));
            startActivity(i);
        });

        boutonPhoto.setOnClickListener(source -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            lanceur.launch(i);
        });
    }
}