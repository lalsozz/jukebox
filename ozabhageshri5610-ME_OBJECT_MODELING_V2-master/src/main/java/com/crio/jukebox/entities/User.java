package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.exceptions.PlayListNotFoundException;

public class User extends BaseEntity {

    private final String name;
    private List<PlayList> playLists;
    private PlayList activePlayList;
    private Song activeSong;

    public String getName() {
        return name;
    }

    public User(String id, String name) {
        this(name);
        this.id = id;
        this.playLists = new ArrayList<>();
    }

    public User(String name) {
        this.name = name;
        this.playLists = new ArrayList<>();
    }

    public void addPlayList(PlayList playList) {
        playLists.add(playList);

    }

    public void deletePlayList(String playListId) {
        playLists.stream().filter(playList -> playList.getId().equals(playListId)).findAny()
                .orElseThrow(() -> new PlayListNotFoundException("Playlist Not Found"));
        playLists.removeIf(c -> c.getId() == playListId);
    }

    public PlayList getPlayList(String playListId) {
        return playLists.stream().filter(playList -> playList.getId().equals(playListId)).findAny()
                .orElseThrow(() -> new PlayListNotFoundException("Playlist Not Found"));
    }

    public void setActivePlayList(PlayList playList) {
        activePlayList = playList;
    }

    public PlayList getActivePlayList() {
        return activePlayList;
    }

    public void setActiveSong(Song song) {
        activeSong = song;
    }

    public Song getActiveSong() {
        return activeSong;
    }

    public List<PlayList> getPlayLists() {
        return playLists;
    }
}
