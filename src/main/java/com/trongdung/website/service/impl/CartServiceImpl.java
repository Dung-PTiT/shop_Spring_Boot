package com.trongdung.website.service.impl;

import com.trongdung.website.dao.CartDAO;
import com.trongdung.website.dao.UserDAO;
import com.trongdung.website.entity.CartEntity;
import com.trongdung.website.model.CartDTO;
import com.trongdung.website.model.UserDTO;
import com.trongdung.website.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public int add(CartDTO cartDTO) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setBuyer(userDAO.getById(cartDTO.getBuyer().getId()));
        cartEntity.setBuyDate(cartDTO.getBuyDate());
        return cartDAO.add(cartEntity);
    }

    @Override
    public void edit(CartDTO cartDTO) {
        CartEntity cartEntity = cartDAO.getById(cartDTO.getId());
        cartEntity.setBuyer(userDAO.getById(cartDTO.getBuyer().getId()));
        cartEntity.setBuyDate(cartDTO.getBuyDate());
        cartDAO.edit(cartEntity);
    }

    @Override
    public void delete(CartDTO cartDTO) {
        CartEntity cartEntity = cartDAO.getById(cartDTO.getId());
        cartDAO.delete(cartEntity);
    }

    @Override
    public CartDTO getById(int id) {
        CartEntity cartEntity = cartDAO.getById(id);
        if(cartEntity != null){
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cartEntity.getId());
                UserDTO userDTO = new UserDTO();
                    userDTO.setId(cartEntity.getBuyer().getId());
                    userDTO.setName(cartEntity.getBuyer().getName());
                    userDTO.setAddress(cartEntity.getBuyer().getAddress());
                    userDTO.setPhoneNumber(cartEntity.getBuyer().getPhoneNumber());
            cartDTO.setBuyer(userDTO);
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            cartDTO.setBuyDate(cartEntity.getBuyDate());
            return  cartDTO;
        }
        return  null;
    }

    @Override
    public List<CartDTO> getAll() {
        List<CartEntity> cartEntities = cartDAO.getAll();
        List<CartDTO> listCart = new ArrayList<>();
        for(CartEntity cartEntity : cartEntities){
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cartEntity.getId());
                UserDTO userDTO = new UserDTO();
                userDTO.setId(cartEntity.getBuyer().getId());
                userDTO.setName(cartEntity.getBuyer().getName());
                userDTO.setAddress(cartEntity.getBuyer().getAddress());
                userDTO.setPhoneNumber(cartEntity.getBuyer().getPhoneNumber());
            cartDTO.setBuyer(userDTO);
            cartDTO.setBuyDate(cartEntity.getBuyDate());
            listCart.add(cartDTO);
        }
        return null;
    }
}
