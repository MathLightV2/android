package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import java.util.List;

public class PlayerActivity extends AppCompatActivity implements Observer{
    Ecouteur ec;
    Modele modele;
    PlayerView playerView;
    ImageView retour;
    ImageButton playStopButton, previousButton, flashBackButton, flashForwardButton, nextButton;
    ExoPlayer player;
    SeekBar seekBarView;
    Handler handler;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Variables
        ec = new Ecouteur();
        playerView = findViewById(R.id.playerView);
        playerView.setUseController(false);
        seekBarView = findViewById(R.id.seekBar);
        handler = new Handler();
        retour = findViewById(R.id.back);
        playStopButton = findViewById(R.id.playStop);
        previousButton = findViewById(R.id.previous);
        flashBackButton = findViewById(R.id.flashBack);
        flashForwardButton = findViewById(R.id.flashForward);
        nextButton = findViewById(R.id.next);

        // Écouteur
        retour.setOnClickListener(ec);
        playStopButton.setOnClickListener(ec);
        previousButton.setOnClickListener(ec);
        flashBackButton.setOnClickListener(ec);
        flashForwardButton.setOnClickListener(ec);
        nextButton.setOnClickListener(ec);
        seekBarView.setOnSeekBarChangeListener(ec);
    }

    @Override
    protected void onStart() {
        super.onStart();
        modele = new Modele(this);
        modele.addObserver(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
            System.out.println(position);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null)
            player.play();
    }

    @Override
    public void changement(List<Music> musiques) {
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        MediaItem mediaItem;
        for (Music musique : musiques){
            mediaItem = MediaItem.fromUri(musique.getSource());
            player.addMediaItem(mediaItem);
        }

        player.seekTo(position, 0);
        player.prepare();
        player.play();
        seekBarChange.run();
    }

    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
        @Override
        public void onClick(View v) {
            if (v == playStopButton){
                // PLAY
                if (!player.isPlaying()){
                    playStopButton.setImageResource(android.R.drawable.ic_media_pause);
                    player.play();
                }
                // PAUSE
                else {
                    player.pause();
                    playStopButton.setImageResource(android.R.drawable.ic_media_play);
                }
            }
            else if (v == previousButton)
                player.seekToPreviousMediaItem();

            else if (v == flashBackButton)
                player.seekTo(player.getCurrentPosition() - 10000 );

            else if (v == flashForwardButton)
                player.seekTo(player.getCurrentPosition() + 10000);

            else if (v == nextButton)
                player.seekToNextMediaItem();

            else if (v == retour){
                setResult(PlayerActivity.RESULT_OK, new Intent());
                finish();
            }
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            player.seekTo(seekBar.getProgress());
        }
    }

    private final Runnable seekBarChange = new Runnable() {
        @Override
        public void run() {
            seekBarView.setMax((int)player.getDuration());
            seekBarView.setProgress((int)player.getCurrentPosition());
            handler.postDelayed(this, 500);
        }
    };
}