package com.trongdung.website.service.impl;

import com.trongdung.website.dao.UserDAO;
import com.trongdung.website.entity.UserEntity;
import com.trongdung.website.model.UserDTO;
import com.trongdung.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int add(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(userDTO.getRole());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setAvatarFileName(userDTO.getAvatarFileName());
        return userDAO.add(userEntity);
    }

    @Override
    public void edit(UserDTO userDTO) {
        UserEntity userEntity = userDAO.getById(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setAge(userDTO.getAge());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(userDTO.getRole());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setAvatarFileName(userDTO.getAvatarFileName());
        userDAO.edit(userEntity);
    }

    @Override
    public void delete(UserDTO userDTO) {
        UserEntity userEntity = userDAO.getById(userDTO.getId());
        userDAO.delete(userEntity);
    }

    @Override
    public UserDTO getById(int id) {
        UserEntity userEntity = userDAO.getById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setAge(userEntity.getAge());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRole(userEntity.getRole());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setAvatarFileName(userEntity.getAvatarFileName());
        return userDTO;
    }

    @Override
    public UserDTO getByUsername(String username) {
        try {
            UserEntity userEntity = userDAO.getByUsername(username);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setName(userEntity.getName());
            userDTO.setAge(userEntity.getAge());
            userDTO.setAddress(userEntity.getAddress());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setRole(userEntity.getRole());
            userDTO.setPhoneNumber(userEntity.getPhoneNumber());
            userDTO.setAvatarFileName(userEntity.getAvatarFileName());
            return userDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UserDTO> getByName(String name) {
        List<UserEntity> userEntities = userDAO.getByName(name);
        List<UserDTO> listUser = new ArrayList<>();
        for (UserEntity u : userEntities) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(u.getId());
            userDTO.setName(u.getName());
            userDTO.setAge(u.getAge());
            userDTO.setAddress(u.getAddress());
            userDTO.setUsername(u.getUsername());
            userDTO.setPassword(u.getPassword());
            userDTO.setRole(u.getRole());
            userDTO.setPhoneNumber(u.getPhoneNumber());
            userDTO.setAvatarFileName(u.getAvatarFileName());
            listUser.add(userDTO);
        }
        return listUser;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities = userDAO.getAll();
        List<UserDTO> listUser = new ArrayList<>();
        for (UserEntity u : userEntities) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(u.getId());
            userDTO.setName(u.getName());
            userDTO.setAge(u.getAge());
            userDTO.setAddress(u.getAddress());
            userDTO.setUsername(u.getUsername());
            userDTO.setPassword(u.getPassword());
            userDTO.setRole(u.getRole());
            userDTO.setPhoneNumber(u.getPhoneNumber());
            userDTO.setAvatarFileName(u.getAvatarFileName());
            listUser.add(userDTO);
        }
        return listUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDAO.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Not Found !");
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new User(username, userEntity.getPassword(), authorities);
    }
}
