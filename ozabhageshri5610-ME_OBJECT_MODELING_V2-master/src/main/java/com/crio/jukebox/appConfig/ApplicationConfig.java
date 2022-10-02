package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlayListCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.LoadSongCommand;
import com.crio.jukebox.commands.ModifyPlayListCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {

    private final ISongRepository songRepository = new SongRepository();
    private final IUserRepository userRepository = new UserRepository();

    private final ISongService songService = new SongService(songRepository);
    private final IUserService userService = new UserService(userRepository, songRepository);

    private final LoadSongCommand loadSongCommand = new LoadSongCommand(songService);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final CreatePlayListCommand createPlayListCommand =
            new CreatePlayListCommand(userService);
    private final DeletePlayListCommand deletePlayListCommand =
            new DeletePlayListCommand(userService);
    private final PlayPlayListCommand playPlayListCommand = new PlayPlayListCommand(userService);
    private final ModifyPlayListCommand modifyPlayListCommand =
            new ModifyPlayListCommand(userService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(userService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("LOAD-DATA", loadSongCommand);
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlayListCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);
        return commandInvoker;
    }
}
