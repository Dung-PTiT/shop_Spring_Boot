package com.trongdung.website.dao.impl;

import com.trongdung.website.dao.ProductDAO;
import com.trongdung.website.entity.CategoryEntity;
import com.trongdung.website.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int add(ProductEntity productEntity) {
        entityManager.persist(productEntity);
        return productEntity.getId();
    }

    @Override
    public void edit(ProductEntity productEntity) {
        entityManager.merge(productEntity);
    }

    @Override
    public void delete(ProductEntity productEntity) {
        entityManager.remove(productEntity);
    }

    @Override
    public ProductEntity getById(int id) {
        String hql = "select p from ProductEntity p where p.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (ProductEntity) query.getSingleResult();
    }

    @Override
    public List<ProductEntity> getByName(String name) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
            Root<ProductEntity> productEntityRoot = criteriaQuery.from(ProductEntity.class);
            Join<ProductEntity, CategoryEntity> productEntityCategoryEntityJoin
                    = productEntityRoot.join("categoryEntity");

            criteriaQuery.select(productEntityRoot);

            if (name != null) {
                Predicate predicate1 = criteriaBuilder.like(productEntityRoot.get("name"), "%" + name + "%");
                Predicate predicate2 = criteriaBuilder.like(productEntityCategoryEntityJoin.get("name"), "%" + name + "%");
                criteriaQuery.where(criteriaBuilder.or(predicate1, predicate2));
            }

            TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ProductEntity> getAll() {
        String hql = "select p from ProductEntity p";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public List<ProductEntity> getByCategory() {
        return null;
    }

}
