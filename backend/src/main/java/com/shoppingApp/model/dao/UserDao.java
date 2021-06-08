package com.shoppingApp.model.dao;

import com.shoppingApp.model.dto.User;

import java.util.List;

public interface UserDao {
  List<User> getAllUsers();
  User getUserById(int user_id);
  User addUser(User user);
  void deleteUserById(int user_id);
  void updateUser(User user);
}
