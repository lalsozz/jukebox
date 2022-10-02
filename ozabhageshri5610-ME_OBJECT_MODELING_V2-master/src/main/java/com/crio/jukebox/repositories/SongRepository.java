package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository {

    private final Map<String, Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository() {
        this.songMap = new HashMap<String, Song>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    @Override
    public Song save(Song entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            entity.setId(String.valueOf(autoIncrement));
            songMap.put(Integer.toString(autoIncrement), entity);
            return entity;
        }
        songMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        return songMap.entrySet().stream().map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
        
    }

    @Override
    public boolean existsById(String id) {
        return songMap.containsKey(id);
    }

    @Override
    public void delete(Song entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        songMap.entrySet().removeIf(entry -> entry.getKey() == id);
    }

    @Override
    public long count() {
        return songMap.size();
    }

    @Override
    public Optional<Song> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
