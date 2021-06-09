package com.shoppingApp.model.dao;

import com.shoppingApp.controllers.ShoppingDataValidationError;
import com.shoppingApp.model.dto.User;

import java.util.List;

public interface UserDao {
  List<User> getAllUsers() throws ShoppingDataValidationError;
  User getUserById(int user_id) throws ShoppingDataValidationError;
  User addUser(User user) throws ShoppingDataValidationError;
  void deleteUserById(int user_id);
  void updateUser(User user) throws ShoppingDataValidationError;
}
