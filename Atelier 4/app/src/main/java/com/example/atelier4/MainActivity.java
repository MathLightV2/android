package com.example.atelier4;

import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;

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

        playerView = findViewById(R.id.playerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ExoPlayer player = new ExoPlayer.Builder(this).build();
        String mp3url = "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/01_-_Intro_-_The_Way_Of_Waking_Up_feat_Alan_Watts.mp3";

        // Assigner le player au PlayerView
        playerView.setPlayer(player);

        // Préparer le contenu multimédia
        MediaItem mediaItem = MediaItem.fromUri(mp3url);
        player.setMediaItem(mediaItem);
        player.prepare();

        // start la lecture
        player.play();
    }
}