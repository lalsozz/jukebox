package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IUserService;

public class PlaySongCommand implements ICommand {
    private IUserService userService;

    public PlaySongCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String pref = tokens.get(2);
        try {
            Song song = userService.playSong(userId, pref);
            System.out.println("Current Song Playing");
            System.out.println("Song - " + song.getSongName());
            System.out.println("Album - " + song.getAlbumName());
            System.out.println("Artists - " + String.join(",", song.getFutureArtists()));

        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
