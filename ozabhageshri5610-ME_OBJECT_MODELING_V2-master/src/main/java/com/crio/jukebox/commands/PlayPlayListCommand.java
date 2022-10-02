package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.services.IUserService;

public class PlayPlayListCommand implements ICommand {

    private IUserService userService;

    public PlayPlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playListId = tokens.get(2);
        try {
            Song song = userService.playPlayList(userId, playListId);
            System.out.println("Current Song Playing");
            System.out.println("Song - " + song.getSongName());
            System.out.println("Album - " + song.getAlbumName());
            System.out.println("Artists - " + String.join(",", song.getFutureArtists()));

        } catch (PlayListNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
