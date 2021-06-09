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
    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user=userDao.addUser(user);

    User fromDao=userDao.getUserById(user.getUser_id());
    assertEquals(user, fromDao);
  }

  @Test
  void getAllUsers() {
    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user=userDao.addUser(user);

    User user2=new User();
    user2.setFirst_name("John2");
    user2.setLast_name("Smith2");
    user2.setPassword("admin2");
    user2=userDao.addUser(user2);

    List<User> users=userDao.getAllUsers();

    assertEquals(2, users.size());
    assertTrue(users.contains(user));
    assertTrue(users.contains(user2));
  }


  @Test
  void deleteUserById() {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);

    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user.getCart().put("testing", 3);
    user=userDao.addUser(user);

    userDao.deleteUserById(user.getUser_id());
    List<User> users=userDao.getAllUsers();
    assertEquals(0, users.size());
  }

  @Test
  void updateUser() {
    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user=userDao.addUser(user);
    User fromDao=userDao.getUserById(user.getUser_id());

    assertEquals(user.getPassword(), fromDao.getPassword());


    user.setPassword("testpwd");
    userDao.updateUser(user);
    fromDao=userDao.getUserById(user.getUser_id());

    assertEquals(user.getPassword(), fromDao.getPassword());
  }
  @Test
  void addAndGetCart() {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);

    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");

    user.getCart().put("testing", 3);
    user=userDao.addUser(user);

    User fromDaoUser=userDao.getUserById(user.getUser_id());
    assertTrue(fromDaoUser.getCart().containsKey(product.getProduct_id()));
    assertEquals(3, fromDaoUser.getCart().get(product.getProduct_id()));

  }

}
