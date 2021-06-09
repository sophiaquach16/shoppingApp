package com.shoppingApp.model.dao;

import com.shoppingApp.TestApplicationConfiguration;
import com.shoppingApp.model.dto.Product;
import com.shoppingApp.model.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class UserDaoImplTest {

  @Autowired
  UserDao userDao;
  @Autowired
  ProductDao productDao;

  @BeforeEach
  void setUp() {
    List<Product> products = productDao.getAllProducts();
    for(Product product : products) {
      productDao.deleteProductById(product.getProduct_id());
    }
    List<User> users = userDao.getAllUsers();
    for(User user : users) {
      userDao.deleteUserById(user.getUser_id());
    }
  }

  @Test
  void addAndGetUserById() {
  }

  @Test
  void getAllUsers() {
  }


  @Test
  void deleteUserById() {
  }

  @Test
  void updateUser() {
  }
  /*tested through add/get tests
  @Test
  void addUserCart() {
  }

  @Test
  void addCartToUser() {
  }
   */
}
