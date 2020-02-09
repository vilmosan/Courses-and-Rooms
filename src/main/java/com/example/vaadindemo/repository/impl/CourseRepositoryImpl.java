package com.example.vaadindemo.repository.impl;

import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Course> findAll() throws Exception {
        return em.createQuery("select n from " + Course.class.getSimpleName() + " n").getResultList();
    }

    @Override
    public void save(Course course) throws Exception {
        em.persist(course);
    }

    @Override
    public void delete(Long id) throws Exception {
        em.remove(findById(id));

    }

    @Override
    public void update(Course course) throws Exception {
        em.merge(course);
    }


    @Override
    public Course findById(Long id) throws Exception {
        return em.find(Course.class, id);
    }

    @Override
    public List<Course> findAllByRoomId(Long id) throws Exception {
        Query query=em.createNamedQuery(Course.FIND_BY_ROOM_ID);
        query.setParameter("roomId",id);
        return query.getResultList();
    }
}