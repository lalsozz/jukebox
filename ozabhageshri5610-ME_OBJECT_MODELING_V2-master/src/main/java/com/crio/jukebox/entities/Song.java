package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {

    private String songName;
    private String genre;
    private String albumName;
    private String albumArtist;
    private List<String> futureArtists;

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public void setFutureArtists(List<String> futureArtists) {
        this.futureArtists = futureArtists;
    }


    public String getSongName() {
        return songName;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public List<String> getFutureArtists() {
        return futureArtists;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
