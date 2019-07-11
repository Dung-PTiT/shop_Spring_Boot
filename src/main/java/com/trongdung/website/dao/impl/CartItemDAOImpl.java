package com.trongdung.website.dao.impl;

import com.trongdung.website.dao.CartItemDAO;
import com.trongdung.website.entity.CartItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class CartItemDAOImpl implements CartItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int add(CartItemEntity cartItemEntity) {
        entityManager.persist(cartItemEntity);
        return  cartItemEntity.getId();
    }

    @Override
    public void edit(CartItemEntity cartItemEntity) {
        entityManager.merge(cartItemEntity);
    }

    @Override
    public void delete(CartItemEntity cartItemEntity) {
        entityManager.remove(cartItemEntity);
    }

    @Override
    public CartItemEntity getById(int id) {
        String hql = "SELECT c from CartItemEntity c where c.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id",id);
        return (CartItemEntity) query.getSingleResult();
    }

    @Override
    public List<CartItemEntity> getAll() {
        String hql = "SELECT c from  CartItemEntity c";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }
}
