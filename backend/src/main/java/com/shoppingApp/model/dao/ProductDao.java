package com.shoppingApp.model.dao;

import com.shoppingApp.controllers.ShoppingDataValidationError;
import com.shoppingApp.model.dto.Product;

import java.util.List;

public interface ProductDao {
  List<Product> getAllProducts();
  Product getProductById(String id);
  Product addProduct(Product product) throws ShoppingDataValidationError;
  void deleteProductById(String product_id);
  void updateProduct(Product product) throws ShoppingDataValidationError;

  void addProductToCart(Product product, int user_id) throws ShoppingDataValidationError;
  void deleteProductFromCart(Product product, int user_id) throws ShoppingDataValidationError;

}
