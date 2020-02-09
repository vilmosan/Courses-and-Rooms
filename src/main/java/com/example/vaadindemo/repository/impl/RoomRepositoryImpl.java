package com.example.vaadindemo.repository.impl;

import com.example.vaadindemo.entity.Room;
import com.example.vaadindemo.repository.RoomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findAll() throws Exception{
        return em.createNamedQuery(Room.FIND_ALL).getResultList();
    }

    @Override
    public void save(Room course) throws Exception{
        em.persist(course);
    }

    @Override
    public void delete(Long id) throws Exception{
        em.remove(findById(id));
    }

    @Override
    public void update(Room course) throws Exception {
        em.merge(course);
    }

    @Override
    public Room findById(Long id) throws Exception{
        return em.find(Room.class, id);
    }

}
