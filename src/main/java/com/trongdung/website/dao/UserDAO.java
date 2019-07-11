package com.trongdung.website.dao;

import com.trongdung.website.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    int add(UserEntity userEntity);

    void edit(UserEntity userEntity);

    void delete(UserEntity userEntity);

    UserEntity getById(int id);

    UserEntity getByUsername(String username);

    List<UserEntity> getByName(String name);

    List<UserEntity> getAll();
}
