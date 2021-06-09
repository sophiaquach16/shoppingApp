package com.shoppingApp.model.dao;

import com.shoppingApp.model.dto.Product;
import com.shoppingApp.model.dto.User;
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
public class UserDaoImpl implements UserDao{
  @Autowired
  JdbcTemplate jdbc;

  @Override
  public User getUserById(int user_id){
    try {
      final String SELECT_USER_BY_ID = "SELECT * FROM User WHERE user_id = ?";
      return addCartToUser(jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), user_id));
    } catch(DataAccessException ex) {
      return null;
    }
  }
  @Override
  public List<User> getAllUsers() {
    final String SELECT_ALL_USERS = "SELECT * FROM User";
    List<User> users= jdbc.query(SELECT_ALL_USERS, new UserMapper());
    for(User user: users){
      user=addCartToUser(user);
    }
    return users;
  }
  @Override
  public User addUser(User user){
    final String INSERT_USER = "INSERT INTO User(password, first_name" +
      ", last_name) VALUES(?,?,?)";
    try {
      jdbc.update(INSERT_USER,
        user.getPassword(),
        user.getFirst_name(),
        user.getLast_name());
      int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
      user.setUser_id(newId);
      addUserCart(user);
    }catch (Exception e){
      return null;
    }
    return user;
  }
  @Override
  @Transactional
  public void deleteUserById(int user_id){
    final String DELETE_USER_IN_CART = "DELETE FROM Cart WHERE user_id = ?";
    jdbc.update(DELETE_USER_IN_CART, user_id);

    final String DELETE_USER = "DELETE FROM User WHERE user_id = ?";
    jdbc.update(DELETE_USER, user_id);

  }
  @Override
  public void updateUser(User user){
    final String UPDATE_PRODUCT= "UPDATE User SET first_name=?, last_name=?" +
      ", password=? WHERE user_id=?";
    try {
      jdbc.update(UPDATE_PRODUCT,
        user.getFirst_name(),
        user.getLast_name(),
        user.getPassword(),
        user.getUser_id());
      addUserCart(user);
    }catch (Exception e){
      return;
    }
  }
  @Transactional
  public void addUserCart(User user){
    //if(user.getCart()==null) return;
    for(String product_id: user.getCart().keySet()) {
      final String INSERT_PRODUCT_IN_CART = "INSERT INTO Cart(product_id, user_id, count) VALUES(?,?,?)";
      final String UPDATE_PRODUCT_IN_CART = "UPDATE Cart SET `count`=? WHERE product_id = ? AND user_id=?";

      try {
        final String SELECT_PRODUCT_BY_ID = "SELECT `count` FROM Cart WHERE user_id = ? AND product_id=?";
        int count = jdbc.queryForObject(SELECT_PRODUCT_BY_ID, Integer.class, user.getUser_id(), product_id);
        jdbc.update(UPDATE_PRODUCT_IN_CART, user.getCart().get(product_id), product_id, user.getUser_id());
      } catch (Exception ex) {
        //error if data empty, so insert instead of update
        jdbc.update(INSERT_PRODUCT_IN_CART, product_id, user.getUser_id(), user.getCart().get(product_id));
        return;
      }
    }
  }

  @Transactional
  public User addCartToUser(User user){
    final String SELECT_ALL_PRODUCT = "SELECT * FROM Product JOIN Cart ON Product.product_id=Cart.product_id WHERE Cart.user_id=?";
    List<Product> products= jdbc.query(SELECT_ALL_PRODUCT, new ProductDaoImpl.ProductMapper(), user.getUser_id());
    final String SELECT_PRODUCT_COUNT = "SELECT `count` FROM Cart WHERE user_id = ? AND product_id=?";
    try {
      for (Product product : products) {
        int count = jdbc.queryForObject(SELECT_PRODUCT_COUNT, Integer.class, user.getUser_id(), product.getProduct_id());
        user.getCart().put(product.getProduct_id(), count);
      }
    }catch (Exception e){
      return null;
    }
    return user;
  }

  public static final class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int index) throws SQLException {
      User user = new User();
      user.setUser_id(rs.getInt("user_id"));
      user.setPassword(rs.getString("password"));
      user.setFirst_name(rs.getString("first_name"));
      user.setLast_name(rs.getString("last_name"));
      return user;
    }
  }

}
