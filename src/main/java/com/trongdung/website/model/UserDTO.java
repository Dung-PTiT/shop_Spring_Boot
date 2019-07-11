package com.trongdung.website.model;

import org.springframework.web.multipart.MultipartFile;

public class UserDTO {
    private int id;
    private String name;
    private int age;
    private String address;
    private String username;
    private String password;
    private String role;
    private String phoneNumber;
    private String avatarFileName;
    private MultipartFile avatarFile;

    public UserDTO() {
    }

    public UserDTO(int id, String name, int age, String address, String username, String password, String role, String phoneNumber, String avatarFileName, MultipartFile avatarFile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.avatarFileName = avatarFileName;
        this.avatarFile = avatarFile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarFileName() {
        return avatarFileName;
    }

    public void setAvatarFileName(String avatarFileName) {
        this.avatarFileName = avatarFileName;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }
}
