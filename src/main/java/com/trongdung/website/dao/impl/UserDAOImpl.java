package com.trongdung.website.dao.impl;

import com.trongdung.website.dao.UserDAO;
import com.trongdung.website.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository// nhu 1 bean
@Transactional// tranh loi khi tuong tac vs DB
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public int add(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity.getId();
    }

    @Override
    public void edit(UserEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public void delete(UserEntity userEntity) {
        entityManager.remove(userEntity);
    }

    @Override
    public UserEntity getById(int id) {
        try{
            String hql = "SELECT u FROM UserEntity u WHERE u.id = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", id);
            return (UserEntity) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public UserEntity getByUsername(String username) {
        try {
            String hql = "SELECT u FROM UserEntity u WHERE u.username = :username";
            Query query = entityManager.createQuery(hql);
            query.setParameter("username", username);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<UserEntity> getByName(String name) {
        try{
            String hql = "SELECT u from UserEntity u where u.name = :name";
            Query query = entityManager.createQuery(hql);
            query.setParameter("name", name);
            return query.getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<UserEntity> getAll() {
        String hql = "select u from UserEntity u";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }
}
