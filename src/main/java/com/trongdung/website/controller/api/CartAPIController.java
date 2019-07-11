package com.trongdung.website.controller.api;

import com.trongdung.website.model.CartItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class CartAPIController {
    @GetMapping(value = "/api/admin/setBuyQuantity")
    @ResponseBody
    public ResponseEntity<?> setBuyQuantity(HttpSession session, @RequestParam("id") int id, @RequestParam("newQuantity") int newQuantity) {
        Map<Integer, CartItemDTO> cartItemDTOMap = (Map<Integer, CartItemDTO>) session.getAttribute("cart");
        cartItemDTOMap.get(id).setBuyQuantity(newQuantity);
        return new ResponseEntity<>("Successfully!", HttpStatus.OK);
    }
}
