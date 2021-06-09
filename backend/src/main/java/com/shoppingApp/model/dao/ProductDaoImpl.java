package com.shoppingApp.model.dao;

import com.shoppingApp.controllers.ShoppingDataValidationError;
import com.shoppingApp.model.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//todo error handling

@Repository
public class ProductDaoImpl implements ProductDao {
  @Autowired
  JdbcTemplate jdbc;

  @Override
  public Product getProductById(String ProductId) {
    try {
      final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Product WHERE product_id = ?";
      return jdbc.queryForObject(SELECT_PRODUCT_BY_ID, new ProductMapper(), ProductId);
    } catch(DataAccessException ex) {
      return null;
    }
  }
  @Override
  public List<Product> getAllProducts() {
    final String SELECT_ALL_PRODUCT = "SELECT * FROM Product";
    return jdbc.query(SELECT_ALL_PRODUCT, new ProductMapper());
  }
  @Override
  public Product addProduct(Product product) throws ShoppingDataValidationError {
    final String INSERT_PRODUCT = "INSERT INTO Product(product_id, product_name, brand" +
      ", product_url, product_rating, discounted_price, image, overall_rating, pid, " +
      "product_category_tree, retail_price, description) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
      jdbc.update(INSERT_PRODUCT,
        product.getProduct_id(),
        product.getProduct_name(),
        product.getBrand(),
        product.getProduct_url(),
        product.getProduct_rating(),
        product.getDiscounted_price(),
        product.getImage(),
        product.getOverall_rating(),
        product.getPid(),
        product.getProduct_category_tree(),
        product.getRetail_price(),
        product.getDescription());
    }catch (Exception e){
      throw new ShoppingDataValidationError(e.getMessage());
    }
    return product;
  }
  @Override
  @Transactional
  public void deleteProductById(String product_id){
    final String DELETE_PRODUCT_IN_CART = "DELETE FROM Cart WHERE product_id = ?";
    jdbc.update(DELETE_PRODUCT_IN_CART, product_id);

    final String DELETE_PRODUCT = "DELETE FROM Product WHERE product_id = ?";
    jdbc.update(DELETE_PRODUCT, product_id);

  }
  @Override
  public void updateProduct(Product product) throws ShoppingDataValidationError {
    final String UPDATE_PRODUCT= "UPDATE Product SET product_name=?, brand=?" +
      ", product_url=?, product_rating=?, discounted_price=?, image=?, overall_rating=?, pid=?, " +
      "product_category_tree=?, retail_price=?, description=? WHERE product_id=?";
    try {
      jdbc.update(UPDATE_PRODUCT,
        product.getProduct_name(),
        product.getBrand(),
        product.getProduct_url(),
        product.getProduct_rating(),
        product.getDiscounted_price(),
        product.getImage(),
        product.getOverall_rating(),
        product.getPid(),
        product.getProduct_category_tree(),
        product.getRetail_price(),
        product.getDescription(),
        product.getProduct_id());

    }catch (Exception e){
      throw new ShoppingDataValidationError(e.getMessage());
    }
  }
  @Override
  public void addProductToCart(Product product, int user_id) throws ShoppingDataValidationError {
    final String ADD_PRODUCT_IN_CART = "UPDATE Cart SET `count`=? WHERE product_id = ? AND user_id=?";
    final String INSERT_PRODUCT_IN_CART = "INSERT INTO Cart(count, product_id, user_id) VALUES(?,?,?)";

    try {
      final String SELECT_PRODUCT_BY_ID = "SELECT `count` FROM Cart WHERE user_id = ? AND product_id=?";
      int count=jdbc.queryForObject(SELECT_PRODUCT_BY_ID, Integer.class, user_id, product.getProduct_id());
      count++;
      jdbc.update(ADD_PRODUCT_IN_CART, count, product.getProduct_id(), user_id);
    } catch(DataAccessException ex) {
      jdbc.update(INSERT_PRODUCT_IN_CART, 1, product.getProduct_id(), user_id);
    }catch (Exception e){
      throw new ShoppingDataValidationError(e.getMessage());
    }
  }
  @Override
  public void deleteProductFromCart(Product product, int user_id) throws ShoppingDataValidationError {
    final String DELETE_PRODUCT_IN_CART = "DELETE FROM Cart WHERE product_id = ? AND user_id=?";
    final String UPDATE_PRODUCT_IN_CART = "UPDATE Cart SET `count`=? WHERE product_id = ? AND user_id=?";

    try {
      final String SELECT_PRODUCT_BY_ID = "SELECT `count` FROM Cart WHERE user_id = ? AND product_id=?";
      int count=jdbc.queryForObject(SELECT_PRODUCT_BY_ID, Integer.class, user_id, product.getProduct_id());
      if(count==1) {
        jdbc.update(DELETE_PRODUCT_IN_CART, product.getProduct_id(), user_id);
      }
      else {
        count--;
        jdbc.update(UPDATE_PRODUCT_IN_CART, count, product.getProduct_id(), user_id);
      }
    } catch(Exception e) {
      throw new ShoppingDataValidationError(e.getMessage());
    }
  }



  public static final class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int index) throws SQLException {
      Product product = new Product();
      product.setProduct_name(rs.getString("product_name"));
      product.setBrand(rs.getString("brand"));
      product.setDescription(rs.getString("description"));
      product.setProduct_rating(rs.getString("product_rating"));
      product.setProduct_id(rs.getString("product_id"));
      product.setProduct_url(rs.getString("product_url"));
      product.setDiscounted_price(rs.getInt("discounted_price"));
      product.setImage(rs.getString("image"));
      product.setOverall_rating(rs.getString("overall_rating"));
      product.setPid(rs.getString("pid"));
      product.setProduct_category_tree(rs.getString("product_category_tree"));
      product.setRetail_price(rs.getInt("retail_price"));
      return product;
    }
  }
}
