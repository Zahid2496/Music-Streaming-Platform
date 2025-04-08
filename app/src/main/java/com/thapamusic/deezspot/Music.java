package com.thapamusic.deezspot;

import java.io.Serializable;

public class Music implements Serializable {
    private String id;
    private String title;
    private String artist;
    private String album;
    private int duration;
    private String imageUrl;
    private String musicUrl;

    public Music(String id, String title, String artist, String album,
                 int duration, String imageUrl, String musicUrl) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.imageUrl = imageUrl;
        this.musicUrl = musicUrl;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public int getDuration() { return duration; }
    public String getImageUrl() { return imageUrl; }
    public String getMusicUrl() { return musicUrl; }
}