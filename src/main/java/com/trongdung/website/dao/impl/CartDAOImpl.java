package com.trongdung.website.dao.impl;

import com.trongdung.website.dao.CartDAO;
import com.trongdung.website.entity.CartEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CartDAOImpl implements CartDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int add(CartEntity cartEntity) {
        entityManager.persist(cartEntity);
        return cartEntity.getId();
    }

    @Override
    public void edit(CartEntity cartEntity) {
        entityManager.merge(cartEntity);
    }

    @Override
    public void delete(CartEntity cartEntity) {
        entityManager.remove(cartEntity);
    }

    @Override
    public CartEntity getById(int id) {
//        String hql = "SELECT c from CartEntity c where c.id = :id";
//        Query query = entityManager.createQuery(hql);
//        query.setParameter("id",id);
//        return (CartEntity) query.getSingleResult();
        return entityManager.find(CartEntity.class,id);
    }

    @Override
    public List<CartEntity> getAll() {
        String hql = "select c from  CartEntity c";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }
}
