package com.trongdung.website.service.impl;

import com.trongdung.website.dao.CartDAO;
import com.trongdung.website.dao.CartItemDAO;
import com.trongdung.website.dao.ProductDAO;
import com.trongdung.website.dao.UserDAO;
import com.trongdung.website.entity.CartEntity;
import com.trongdung.website.entity.CartItemEntity;
import com.trongdung.website.model.CartDTO;
import com.trongdung.website.model.CartItemDTO;
import com.trongdung.website.model.ProductDTO;
import com.trongdung.website.model.UserDTO;
import com.trongdung.website.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemDAO cartItemDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public int add(CartItemDTO cartItemDTO) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProduct(productDAO.getById(cartItemDTO.getProductDTO().getId()));
        cartItemEntity.setBuyQuantity(cartItemDTO.getBuyQuantity());
        cartItemEntity.setSellPrice(cartItemDTO.getSellPrice());
        cartItemEntity.setCart(cartDAO.getById(cartItemDTO.getCartDTO().getId()));
        return cartItemDAO.add(cartItemEntity);
    }

    @Override
    public void edit(CartItemDTO cartItemDTO) {
        CartItemEntity cartItemEntity = cartItemDAO.getById(cartItemDTO.getId());
        cartItemEntity.setProduct(productDAO.getById(cartItemDTO.getProductDTO().getId()));
        cartItemEntity.setBuyQuantity(cartItemDTO.getBuyQuantity());
        cartItemEntity.setSellPrice(cartItemDTO.getSellPrice());
        cartItemEntity.setCart(cartDAO.getById(cartItemDTO.getCartDTO().getId()));
        cartItemDAO.edit(cartItemEntity);
    }

    @Override
    public void delete(CartItemDTO cartItemDTO) {
        CartItemEntity cartItemEntity = cartItemDAO.getById(cartItemDTO.getId());
        cartItemDAO.delete(cartItemEntity);
    }

    @Override
    public CartItemDTO getById(int id) {
        CartItemEntity cartItemEntity = cartItemDAO.getById(id);
        if(cartItemEntity != null){
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            ProductDTO productDTO = new ProductDTO();
                productDTO.setId(cartItemEntity.getProduct().getId());
                productDTO.setName(cartItemEntity.getProduct().getName());
                productDTO.setPrice(cartItemEntity.getProduct().getPrice());
                productDTO.setQuantity(cartItemEntity.getProduct().getQuantity());
            cartItemDTO.setProductDTO(productDTO);
            cartItemDTO.setBuyQuantity(cartItemEntity.getBuyQuantity());
            cartItemDTO.setSellPrice(cartItemEntity.getSellPrice());
            CartDTO cartDTO = new CartDTO();
                cartDTO.setId(cartItemEntity.getCart().getId());
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(cartItemEntity.getCart().getBuyer().getId());
                    userDTO.setName(cartItemEntity.getCart().getBuyer().getName());
                    userDTO.setAddress(cartItemEntity.getCart().getBuyer().getAddress());
                    userDTO.setPhoneNumber(cartItemEntity.getCart().getBuyer().getPhoneNumber());
                cartDTO.setBuyer(userDTO);
                cartDTO.setBuyDate(cartItemEntity.getCart().getBuyDate());
            cartItemDTO.setCartDTO(cartDTO);
            return cartItemDTO;
        }
        return null;
    }

    @Override
    public List<CartItemDTO> getAll() {
        List<CartItemEntity> cartItemEntities = new ArrayList<>();
        List<CartItemDTO> cartItems = new ArrayList<>();
        for(CartItemEntity c : cartItemEntities){
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(c.getId());
                ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(c.getProduct().getId());
                    productDTO.setName(c.getProduct().getName());
                    productDTO.setPrice(c.getProduct().getPrice());
                    productDTO.setQuantity(c.getProduct().getQuantity());
            cartItemDTO.setProductDTO(productDTO);
            cartItemDTO.setBuyQuantity(c.getBuyQuantity());
            cartItemDTO.setSellPrice(c.getSellPrice());
                CartDTO cartDTO = new CartDTO();
                cartDTO.setId(c.getCart().getId());
                    UserDTO userDTO = new UserDTO();
                        userDTO.setId(c.getCart().getBuyer().getId());
                        userDTO.setName(c.getCart().getBuyer().getName());
                        userDTO.setAddress(c.getCart().getBuyer().getAddress());
                        userDTO.setPhoneNumber(c.getCart().getBuyer().getPhoneNumber());
                cartDTO.setBuyer(userDTO);
                cartDTO.setBuyDate(c.getCart().getBuyDate());
            cartItemDTO.setCartDTO(cartDTO);
            cartItems.add(cartItemDTO);
        }
        return cartItems;
    }
}
