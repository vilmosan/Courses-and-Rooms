package com.example.vaadindemo.repository;

import com.example.vaadindemo.entity.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> findAll() throws Exception;

    void save(Room course) throws Exception;
    void update(Room course) throws Exception;
    void delete(Long id) throws Exception;
    Room findById(Long id) throws Exception;
}
