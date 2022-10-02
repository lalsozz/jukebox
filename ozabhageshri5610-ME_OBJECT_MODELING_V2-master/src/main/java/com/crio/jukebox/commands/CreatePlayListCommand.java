package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IUserService;

public class CreatePlayListCommand implements ICommand {
    private IUserService userService;

    public CreatePlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playListName = tokens.get(2);
        tokens = tokens.subList(3, tokens.size());
        try {
            PlayList playList = userService.createPlayList(userId, playListName, tokens);
            System.out.println("Playlist ID - " + playList.getId());
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
