package com.trongdung.website.service;

import com.trongdung.website.model.UserDTO;

import java.util.List;

public interface UserService {
    int add(UserDTO userDTO);

    void edit(UserDTO userDTO);

    void delete(UserDTO userDTO);

    UserDTO getById(int id);

    UserDTO getByUsername(String username);

    List<UserDTO> getByName(String name);

    List<UserDTO> getAll();
}
