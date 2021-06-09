package com.shoppingApp.model.dao;

import com.shoppingApp.TestApplicationConfiguration;
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
  void addAndGetProductById() {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);
    Product fromDao=productDao.getProductById(product.getProduct_id());
    assertEquals(product, fromDao);

  }

  @Test
  void getAllProducts() {
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
  void deleteProductById() {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);
    productDao.deleteProductById(product.getProduct_id());
    List<Product> products=productDao.getAllProducts();
    assertEquals(0, products.size());
  }

  @Test
  void updateProduct() {
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
  void addAndDeleteProductToCart() {
    Product product=new Product();
    product.setProduct_id("testing");
    product=productDao.addProduct(product);

    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user=userDao.addUser(user);
  }



}
