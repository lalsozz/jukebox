package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {

    private final Map<String, User> userMap;
    private Integer autoIncrement;

    public UserRepository() {
        this.userMap = new HashMap<>();
        this.autoIncrement = 0;
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if (entity.getId() == null) {
            autoIncrement++;
            User user = new User(Integer.toString(autoIncrement), entity.getName());
            this.userMap.put(user.getId(), user);
            return user;
        }
        this.userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        return this.userMap.entrySet().stream().map((Entry<String, User> entry) -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(this.userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return this.userMap.containsKey(id);
    }

    @Override
    public void delete(User entity) {
        this.deleteById(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        if (this.userMap.containsKey(id))
            this.userMap.remove(id);

    }

    @Override
    public long count() {
        return this.userMap.size();
    }

}
