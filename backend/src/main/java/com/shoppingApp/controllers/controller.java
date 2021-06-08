package com.shoppingApp.controllers;

import com.shoppingApp.model.dao.ProductDao;
import com.shoppingApp.model.dao.UserDao;
import com.shoppingApp.model.dto.Product;
import com.shoppingApp.model.dto.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class controller {
  @Autowired
  ProductDao productDao;

  @Autowired
  UserDao userDao;

  //get all products
  @GetMapping("allProducts")
  public List<Product> displayProducts() {
    return productDao.getAllProducts();
  }
  //get cart for user
  @GetMapping("cart")
  public HashMap<Product, Integer> getCartForUser(int id) {
    User user=userDao.getUserById(id);
    return user.getCart();
  }
  //add product to cart
  @PutMapping("{id}/addProductToCart")
  public ResponseEntity addProductToCart(@PathVariable int id, @ReqestBody Product product){
    ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    if(productDao.getProductById(product.getUnique_id())==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    else if(userDao.getUserById(id)==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    productDao.addProductToCart(product, id);
    return response;
  }
  //delete product from cart
  @DeleteMapping("{id}/removeProductFromCart")
  public ResponseEntity removeProductToCart(@PathVariable int id, @ReqestBody Product product){
    ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    if(productDao.getProductById(product.getUnique_id())==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    else if(userDao.getUserById(id)==null){
      response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
      return response;
    }
    productDao.deleteProductFromCart(product, id);
    return response;
  }
}
