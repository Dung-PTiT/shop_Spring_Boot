package com.trongdung.website.controller.AdminController;

import com.trongdung.website.model.CartDTO;
import com.trongdung.website.model.CartItemDTO;
import com.trongdung.website.model.ProductDTO;
import com.trongdung.website.model.UserDTO;
import com.trongdung.website.service.CartItemService;
import com.trongdung.website.service.CartService;
import com.trongdung.website.service.ProductService;
import com.trongdung.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;


    @GetMapping(value = "/cart/add/{productId}")
    public String addCartItem(@PathVariable("productId") int id, HttpSession session) {
        ProductDTO productDTO = productService.getById(id);
        if (productDTO != null) {
            Object obj = session.getAttribute("cart");
            if (obj == null) {
                CartItemDTO cartItem = new CartItemDTO();
                cartItem.setBuyQuantity(1);
                cartItem.setProductDTO(productDTO);
                cartItem.setSellPrice(productDTO.getPrice());
                Map<Integer, CartItemDTO> mapCartItem = new HashMap<>();
                mapCartItem.put(productDTO.getId(), cartItem);
                session.setAttribute("cart", mapCartItem);
            } else {
                Map<Integer, CartItemDTO> mapCartItem = (Map<Integer, CartItemDTO>) obj;
                CartItemDTO item = mapCartItem.get(productDTO.getId());
                if (item == null) {
                    CartItemDTO cartItem = new CartItemDTO();
                    cartItem.setBuyQuantity(1);
                    cartItem.setProductDTO(productDTO);
                    cartItem.setSellPrice(productDTO.getPrice());
                    mapCartItem.put(productDTO.getId(), cartItem);
                }
//                } else {
//                    item.setBuyQuantity(item.getBuyQuantity() + 1);
//                }
                session.setAttribute("cart", mapCartItem);
            }
        }
        return "redirect:/admin/cart";
    }

    @GetMapping(value = "/cart")
    public String cart(Model model, HttpServletRequest httpServletRequest) {
        List<UserDTO> listUser = userService.getAll();
        httpServletRequest.setAttribute("listUser", listUser);
        return "admin/cart/cart";
    }

    @GetMapping(value = "/cart/delete/{productId}")
    public String deleteCartItem(@PathVariable("productId") int id, HttpSession session, HttpServletRequest req) {
        HttpSession sessionItem = req.getSession();
        Object obj = sessionItem.getAttribute("cart");
        if (obj != null) {

            Map<Integer, CartItemDTO> mapCartItem = (Map<Integer, CartItemDTO>) obj;
            mapCartItem.remove(id);
            session.setAttribute("cart", mapCartItem);
        }
        return "redirect:/admin/cart";
    }

    @PostMapping(value = "/cart/order/add")
    public String addOrder(@RequestParam(name = "buyerId") int id, HttpSession session, HttpServletRequest req) {
        CartDTO cartDTO = new CartDTO();
        UserDTO userDTO = userService.getById(id);
        cartDTO.setBuyer(userDTO);
        cartDTO.setBuyDate(new Date());
        cartDTO.setId(cartService.add(cartDTO));
        Object obj = session.getAttribute("cart");
        if (obj != null) {
            Map<Integer, CartItemDTO> mapCartItem = (Map<Integer, CartItemDTO>) obj;
            Set<Integer> keys = mapCartItem.keySet();
            for (Integer k : keys) {
                CartItemDTO cartItemDTO = mapCartItem.get(k);
                cartItemDTO.setCartDTO(cartDTO);
                cartItemService.add(cartItemDTO);
            }
        }
        return "redirect:/admin/order/list";
    }

    @GetMapping(value = "/order/list")
    public String orderList() {
        return "admin/cart/orderInfo";
    }

}

