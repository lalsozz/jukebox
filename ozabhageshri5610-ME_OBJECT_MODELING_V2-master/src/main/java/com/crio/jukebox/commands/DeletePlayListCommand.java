package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.services.IUserService;

public class DeletePlayListCommand implements ICommand {
    private IUserService userService;

    public DeletePlayListCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playListId = tokens.get(2);
        try {
            userService.deletePlayList(userId, playListId);
            System.out.println("Delete Successful");
        } catch (PlayListNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
