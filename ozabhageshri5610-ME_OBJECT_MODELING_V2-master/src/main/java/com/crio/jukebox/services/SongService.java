package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements ISongService {

    private final ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void loadData(String fileName) {

        boolean processed = true;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            String splitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] songVals = line.split(splitBy);
                Song instance = new Song();
                instance.setSongName(songVals[0]);
                instance.setGenre(songVals[1]);
                instance.setAlbumName(songVals[2]);
                instance.setAlbumArtist(songVals[3]);
                String[] futureArtists = songVals[4].split("#");
                instance.setFutureArtists(Arrays.asList(futureArtists));
                this.songRepository.save(instance);
            }
        } catch (IOException e) {
            processed = false;
            e.printStackTrace();
        }
        if (processed)
            System.out.println("Songs Loaded successfully");
    }

}
