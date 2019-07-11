package com.trongdung.website.controller.AdminController;

import com.trongdung.website.model.UserDTO;
import com.trongdung.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    // Add User
    @GetMapping("/user/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserDTO());
        return "admin/user/addUser";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") UserDTO userDTO) {
        if (userDTO.getAvatarFile() != null && !userDTO.getAvatarFile().isEmpty()) {
            try {
                final String UPLOAD_FOLDER = "D:\\user";
                String image = System.currentTimeMillis() + ".jpg";
                Path pathAvatar = Paths.get(UPLOAD_FOLDER + File.separator + image);
                Files.write(pathAvatar, userDTO.getAvatarFile().getBytes());
                userDTO.setAvatarFileName(image);
                int userId = userService.add(userDTO);
            } catch (Exception e) {
                System.out.println("Loi: " + e);
            }
        }
        return "redirect:/admin/user/list";
    }

    // Edit User
    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        UserDTO userDTO = userService.getById(id);
        model.addAttribute("userEdit", userDTO);
        return "admin/user/editUser";
    }

    @PostMapping("/user/{id}/edit")
    public String editUser(@ModelAttribute("userEdit") UserDTO userDTO) {
        userService.edit(userDTO);
        return "redirect:/admin/user/list";
    }

    //Delete User
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") int id, UserDTO userDTO) {
        userDTO = userService.getById(id);
        userService.delete(userDTO);
        return "redirect:/admin/user/list";
    }

    // Show list user
    @GetMapping("/user/list")
    public String listUser(Model model) {
        List<UserDTO> listUsers = userService.getAll();
        model.addAttribute("users", listUsers);
        return "admin/user/userList";
    }

    // Get User by ID
    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("u", userService.getById(id));
        return "admin/user/user";
    }

    // Search user by name
    @GetMapping("/user/searchByName")
    public String searchUserByName(@RequestParam(name = "name") String name, Model model) {
        List<UserDTO> listUsers = userService.getByName(name);
        model.addAttribute("users", listUsers);
        return "admin/user/userList";
    }

    //  Search user by username
    @GetMapping("/user/searchByUsername")
    public String searchUserByUsername(@RequestParam(name = "username") String username, Model model) {
        UserDTO userDTO = userService.getByUsername(username);
        model.addAttribute("users", userDTO);
        return "admin/user/userList";
    }
}
