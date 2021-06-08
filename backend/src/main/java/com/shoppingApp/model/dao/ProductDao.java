package com.shoppingApp.model.dao;

import com.shoppingApp.model.dto.Product;

import java.util.List;

public interface ProductDao {
  List<Product> getAllProducts();
  Product getProductById(String id);
  Product addProduct(Product product);
  void deleteProductById(String product_id);
  void updateProduct(Product product);

  void addProductToCart(Product product, int user_id);
  void deleteProductFromCart(Product product, int user_id);

}
