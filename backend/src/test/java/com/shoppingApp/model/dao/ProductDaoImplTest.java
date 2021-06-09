package com.shoppingApp.model.dao;

import com.shoppingApp.TestApplicationConfiguration;
import com.shoppingApp.controllers.ShoppingDataValidationError;
import com.shoppingApp.model.dto.Product;
import com.shoppingApp.model.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class ProductDaoImplTest {

  @Autowired
  UserDao userDao;
  @Autowired
  ProductDao productDao;

  @BeforeEach
  void setUp() throws ShoppingDataValidationError {
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
  void addAndGetProductById() throws ShoppingDataValidationError {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);
    Product fromDao=productDao.getProductById(product.getProduct_id());
    assertEquals(product, fromDao);

  }

  @Test
  void getAllProducts() throws ShoppingDataValidationError {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);

    Product product2=new Product();
    product2.setProduct_id("testing2");
    product2=productDao.addProduct(product2);

    List<Product> products=productDao.getAllProducts();

    assertEquals(2, products.size());
    assertTrue(products.contains(product));
    assertTrue(products.contains(product2));
  }

  @Test
  void deleteProductById() throws ShoppingDataValidationError {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);

    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user.getCart().put("testing", 3);
    user=userDao.addUser(user);


    productDao.deleteProductById(product.getProduct_id());
    List<Product> products=productDao.getAllProducts();
    assertEquals(0, products.size());
  }

  @Test
  void updateProduct() throws ShoppingDataValidationError {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);
    Product fromDao=productDao.getProductById(product.getProduct_id());

    assertEquals(product.getRetail_price(), fromDao.getRetail_price());


    product.setRetail_price(30);
    productDao.updateProduct(product);
    fromDao=productDao.getProductById(product.getProduct_id());

    assertEquals(product.getRetail_price(), fromDao.getRetail_price());

  }


  @Test
  void addAndDeleteProductToCart() throws ShoppingDataValidationError {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);

    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user=userDao.addUser(user);

    productDao.addProductToCart(product, user.getUser_id());

    User fromDaoUser=userDao.getUserById(user.getUser_id());
    assertTrue(fromDaoUser.getCart().containsKey(product.getProduct_id()));
    assertEquals(1, fromDaoUser.getCart().get(product.getProduct_id()));

    productDao.addProductToCart(product, user.getUser_id());
    fromDaoUser=userDao.getUserById(user.getUser_id());
    assertEquals(2, fromDaoUser.getCart().get(product.getProduct_id()));

    productDao.deleteProductFromCart(product, user.getUser_id());
    fromDaoUser=userDao.getUserById(user.getUser_id());
    assertEquals(1, fromDaoUser.getCart().get(product.getProduct_id()));

    productDao.deleteProductFromCart(product, user.getUser_id());
    fromDaoUser=userDao.getUserById(user.getUser_id());
    assertEquals(0, fromDaoUser.getCart().size());
  }
}
