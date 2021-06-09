package com.shoppingApp.controllers;

import com.shoppingApp.model.dao.ProductDao;
import com.shoppingApp.model.dao.UserDao;
import com.shoppingApp.model.dto.Product;
import com.shoppingApp.model.dto.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;

//todo test and error handling

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/")
public class controller {
  @Autowired
  ProductDao productDao;

  @Autowired
  UserDao userDao;


  //for testing: return a test user
  //TODO add requestbody
  @GetMapping("user")
  public User createAndGetUser() throws ShoppingDataValidationError {
    User user=new User();
    user.setFirst_name("John");
    user.setLast_name("Smith");
    user.setPassword("admin");
    user.getCart().put("05324708277dbf2f3e8b55a660e91988", 2);
    user=userDao.addUser(user);
    return user;
  }
  //get all products
  @GetMapping("allProducts")
  public List<Product> displayProducts() {
    List<Product> products = productDao.getAllProducts();
    return productDao.getAllProducts();
  }

  //get product by id
  @GetMapping("{product_id}/product")
  public Product getProduct(@PathVariable String product_id){return productDao.getProductById(product_id);}

  //get cart for user
  @GetMapping("{id}/cart")
  public HashMap<Product, Integer> getCartForUser(@PathVariable int id) throws ShoppingDataValidationError {
    User user=userDao.getUserById(id);
    HashMap<Product, Integer> output=new HashMap<>();
    for(String product_id: user.getCart().keySet()){
      output.put(productDao.getProductById(product_id), user.getCart().get(product_id));
      System.out.println(productDao.getProductById(product_id));
    }
    return output;
  }

  //add product to cart
  @PutMapping("{id}/addProductToCart")
  public ResponseEntity addProductToCart(@PathVariable int id, @RequestBody String product_id) throws ShoppingDataValidationError {
    ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    if(productDao.getProductById(product_id)==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    else if(userDao.getUserById(id)==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    Product product=productDao.getProductById(product_id);
    productDao.addProductToCart(product, id);
    return response;
  }

  //delete product from cart
  @DeleteMapping("{id}/removeProductFromCart")
  public ResponseEntity removeProductToCart(@PathVariable int id, @RequestBody String product_id) throws ShoppingDataValidationError {
    ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    if(productDao.getProductById(product_id)==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    else if(userDao.getUserById(id)==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    productDao.deleteProductFromCart(productDao.getProductById(product_id), id);
    return response;
  }
}
