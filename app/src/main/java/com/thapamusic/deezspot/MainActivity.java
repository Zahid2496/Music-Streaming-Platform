package com.thapamusic.deezspot;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView musicRecyclerView;
    private MusicAdapter musicAdapter;
    private List<Music> musicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicRecyclerView = findViewById(R.id.music_recycler_view);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize music list
        musicList = new ArrayList<>();
        musicList.add(new Music("1", "Blinding Lights", "The Weeknd", "After Hours",
                203, "https://i.imgur.com/7jyzJbD.jpg", "https://example.com/music1.mp3"));
        musicList.add(new Music("2", "Save Your Tears", "The Weeknd", "After Hours",
                215, "https://i.imgur.com/7jyzJbD.jpg", "https://example.com/music2.mp3"));
        musicList.add(new Music("3", "Levitating", "Dua Lipa", "Future Nostalgia",
                218, "https://i.imgur.com/5XyW3fY.jpg", "https://example.com/music3.mp3"));
        musicList.add(new Music("4", "Don't Start Now", "Dua Lipa", "Future Nostalgia",
                183, "https://i.imgur.com/5XyW3fY.jpg", "https://example.com/music4.mp3"));
        musicList.add(new Music("5", "Watermelon Sugar", "Harry Styles", "Fine Line",
                174, "https://i.imgur.com/9qkZQvD.jpg", "https://example.com/music5.mp3"));

        musicAdapter = new MusicAdapter(this, musicList);
        musicRecyclerView.setAdapter(musicAdapter);
    }
}