package com.thapamusic.deezspot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MusicDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MusicDetailsActivity";

    private ImageView musicImage;
    private TextView title, artist, album;
    private Button playButton;
    private Music currentMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);

        // Initialize views
        musicImage = findViewById(R.id.details_image);
        title = findViewById(R.id.details_title);
        artist = findViewById(R.id.details_artist);
        album = findViewById(R.id.details_album);
        playButton = findViewById(R.id.play_button);

        // Get music data from intent
        try {
            currentMusic = (Music) getIntent().getSerializableExtra("music");
            if (currentMusic == null) {
                Log.e(TAG, "No music data received");
                finish();
                return;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error parsing music data", e);
            finish();
            return;
        }

        // Load music details
        Glide.with(this)
                .load(currentMusic.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_music_note)
                        .error(R.drawable.ic_music_note))
                .into(musicImage);

        title.setText(currentMusic.getTitle());
        artist.setText(currentMusic.getArtist());
        album.setText(currentMusic.getAlbum());

        // Set up play button
        playButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(this, MusicPlayerService.class);
            serviceIntent.setAction(MusicPlayerService.ACTION_PLAY);
            serviceIntent.putExtra("music", currentMusic);
            startService(serviceIntent);
        });
    }
}