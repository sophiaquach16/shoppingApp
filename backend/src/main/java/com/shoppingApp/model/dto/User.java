package com.shoppingApp.model.dto;

import java.util.HashMap;

public class User {


  HashMap<Product, Integer> cart;
  int user_id;
  String first_name;
  String last_name;
  String password;

  public HashMap<Product, Integer> getCart() {
    return cart;
  }

  public void setCart(HashMap<Product, Integer> cart) {
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
}
