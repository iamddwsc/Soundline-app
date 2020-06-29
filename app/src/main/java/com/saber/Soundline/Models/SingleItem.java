package com.saber.Soundline.Models;

public class SingleItem {

    private String trackName, artist, photo;

    public SingleItem() {

    }

    public SingleItem(String trackName, String artist, String photo) {
        this.trackName = trackName;
        this.artist = artist;
        this.photo = photo;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
