package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public interface IUserService {

    public User create(String name);

    public PlayList createPlayList(String userId,String playListName,List<String> songIds);

    public void deletePlayList(String userId,String playListId);

    public PlayList modifyPlayList(String userId,String action,String playListId,List<String> songIds);

    public Song playPlayList(String userId,String playListId);

    public Song playSong(String userId, String pref);

}
