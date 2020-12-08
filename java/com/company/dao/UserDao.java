package com.company.dao;



public interface UserDao {
    model.User get(int id);
    void create(User item);
    <User> User get(String email);
}
