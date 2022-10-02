package com.crio.jukebox.entities;

import java.util.List;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlayList extends BaseEntity {
    private final String name;
    private final List<Song> songs;

    public PlayList(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public void addSong(Song song) {
        long count = songs.stream().filter(val -> val.getId() == song.getId()).count();
        if (count == 0)
            songs.add(song);
    }

    public void deleteSong(Song song) {
        songs.stream().filter(val -> val.getId() == song.getId()).findAny()
                .orElseThrow(() -> new SongNotFoundException(
                        "Some Requested Songs for Deletion are not present in the playlist. Please try again."));
        songs.removeIf(val -> val.getId() == song.getId());
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
