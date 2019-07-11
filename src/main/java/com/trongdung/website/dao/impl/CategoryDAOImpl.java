package com.trongdung.website.dao.impl;

import com.trongdung.website.dao.CategoryDAO;
import com.trongdung.website.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(CategoryEntity categoryEntity) {
        entityManager.persist(categoryEntity);
    }

    @Override
    public void edit(CategoryEntity categoryEntity) {
        entityManager.merge(categoryEntity);
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        entityManager.remove(categoryEntity);
    }

    @Override
    public CategoryEntity getById(int id) {
        String hql = "select c from CategoryEntity c where c.id = : id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (CategoryEntity) query.getSingleResult();
    }

    @Override
    public CategoryEntity getByName(String name) {
//        String hql = "select c from CategoryEntity c where c.name = : name";
//        Query query = entityManager.createQuery(hql);
//        query.setParameter("name", name);
//        return (CategoryEntity) query.getSingleResult();
        //get with function
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //set model return ex: categoryEntity
        CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        //... from...to
        Root<CategoryEntity> category = criteriaQuery.from(CategoryEntity.class);

//        List<Predicate> predicates = new ArrayList<>();

        if(name != null){
            Predicate predicate1 = criteriaBuilder.like(category.get("name"), "%" + name + "%");
//            predicates.add(predicate1);
            criteriaQuery.where(predicate1);
        }
//
//        for(Predicate predicate : predicates){
//            criteriaQuery.where(predicate);
//        }

        criteriaQuery.select(category);

        TypedQuery<CategoryEntity> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public List<CategoryEntity> getAll() {
        String hql = "SELECT c from CategoryEntity c";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }
}
