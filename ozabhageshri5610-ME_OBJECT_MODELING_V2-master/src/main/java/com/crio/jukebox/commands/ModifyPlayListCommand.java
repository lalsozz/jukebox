package com.crio.jukebox.commands;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IUserService;

public class ModifyPlayListCommand implements ICommand {

    private IUserService userService;

    public ModifyPlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String action = tokens.get(1);
        String userId = tokens.get(2);
        String playListId = tokens.get(3);
        tokens = tokens.subList(4, tokens.size());
        try {
            PlayList playList = userService.modifyPlayList(userId, action, playListId, tokens);
            System.out.println("Playlist ID - " + playListId);
            System.out.println("Playlist Name - " + playList.getName());
            List<String> ids = playList.getSongs().stream().map(val -> val.getId())
                    .collect(Collectors.toList());

            System.out.println("Song IDs - " + String.join(" ", ids));
        } catch (PlayListNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
