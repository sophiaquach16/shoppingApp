package com.shoppingApp.model.dto;

import java.util.HashMap;
import java.util.Objects;

public class User {


  HashMap<String, Integer> cart=new HashMap<>();
  int user_id;
  String first_name;
  String last_name;
  String password;

  public HashMap<String, Integer> getCart() {
    return cart;
  }

  public void setCart(HashMap<String, Integer> cart) {
    this.cart = cart;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return user_id == user.user_id && Objects.equals(cart, user.cart) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cart, user_id, first_name, last_name, password);
  }
}
