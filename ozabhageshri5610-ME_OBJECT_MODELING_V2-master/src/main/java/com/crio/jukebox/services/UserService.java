package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ISongRepository songRepository;

    public UserService(IUserRepository userRepository, ISongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public User create(String name) {
        return this.userRepository.save(new User(name));
    }

    @Override
    public PlayList createPlayList(String userId, String playListName, List<String> songIds) {
        List<Song> songs = new ArrayList<>();
        songIds.forEach((songId) -> {
            Song song = songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException(
                    "Some Requested Songs Not Available. Please try again."));
            songs.add(song);
        });
        PlayList playList = new PlayList(playListName, songs);
        User user =
                userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        playList.setId(String.valueOf(user.getPlayLists().size() + 1));
        user.addPlayList(playList);
        return playList;
    }

    @Override
    public void deletePlayList(String userId, String playListId) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        user.deletePlayList(playListId);
    }

    @Override
    public PlayList modifyPlayList(String userId, String action, String playListId,
            List<String> songIds) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        PlayList playList = user.getPlayList(playListId);
        List<Song> songs = new ArrayList<>();
        songIds.forEach((songId) -> {
            songs.add(songRepository.findById(songId).orElseThrow(() -> new SongNotFoundException(
                    "Some Requested Songs Not Available. Please try again.")));
        });
        if (action.equalsIgnoreCase("ADD-SONG")) {
            songs.forEach(song -> playList.addSong(song));
        } else {
            songs.forEach(song -> playList.deleteSong(song));
        }
        return playList;

    }

    @Override
    public Song playPlayList(String userId, String playListId) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        PlayList playList = user.getPlayList(playListId);
        user.setActivePlayList(playList);
        if (playList.getSongs().size() > 0) {
            user.setActiveSong(playList.getSongs().get(0));
            return user.getActiveSong();
        }
        return null;

    }

    @Override
    public Song playSong(String userId, String pref) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(""));
        PlayList playList = user.getActivePlayList();
        Song active = user.getActiveSong();
        if (pref.equalsIgnoreCase("BACK")) {
            int current = playList.getSongs().indexOf(active);
            if (current == 0) {
                int size = playList.getSongs().size();
                active = playList.getSongs().get(size - 1);
            } else {
                current--;
                active = playList.getSongs().get(current);
            }
        } else if (pref.equalsIgnoreCase("NEXT")) {
            int current = playList.getSongs().indexOf(active);
            if (current == playList.getSongs().size() - 1) {
                active = playList.getSongs().get(0);
            } else {
                current++;
                active = playList.getSongs().get(current);
            }
        } else {
            String songId = pref;
            playList.getSongs().stream().filter(val -> val.getId().equals(songId)).findAny()
                    .orElseThrow(() -> new SongNotFoundException(
                            "Given song id is not a part of the active playlist"));
            active = songRepository.findById(songId)
                    .orElseThrow(() -> new SongNotFoundException(""));
        }
        user.setActiveSong(active);
        return active;

    }

}
