package com.example.wensql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomController {
    @Autowired
    private RoomRepo repo;

    public Room Get(int i) {
        try {
            return repo.findById(i).get();
        } catch (Exception e) {
            return null;
        }
    }

    void Delete(int id) {
        repo.deleteById(id);
    }

    public void Update(Room room) {
        int i = room.getRoomId();
        Delete(i);
        repo.save(room);
    }

    public List<Room> GetAll() {
        return repo.findAll();
    }

    public void Add(Room room) {
        repo.save(room);
    }
}
