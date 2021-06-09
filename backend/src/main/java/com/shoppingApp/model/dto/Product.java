package com.shoppingApp.model.dto;


import java.util.Objects;

public class Product {
  String product_id;
  String product_url;
  String product_name;
  String product_category_tree;
  String pid;
  int retail_price;
  int discounted_price;
  String image;
  String description;
  String product_rating;
  String overall_rating;
  String brand;

  public String getProduct_id() {
    return product_id;
  }

  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }

  public String getProduct_url() {
    return product_url;
  }

  public void setProduct_url(String product_url) {
    this.product_url = product_url;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getProduct_category_tree() {
    return product_category_tree;
  }

  public void setProduct_category_tree(String product_category_tree) {
    this.product_category_tree = product_category_tree;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public int getRetail_price() {
    return retail_price;
  }

  public void setRetail_price(int retail_price) {
    this.retail_price = retail_price;
  }

  public int getDiscounted_price() {
    return discounted_price;
  }

  public void setDiscounted_price(int discounted_price) {
    this.discounted_price = discounted_price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getProduct_rating() {
    return product_rating;
  }

  public void setProduct_rating(String product_rating) {
    this.product_rating = product_rating;
  }

  public String getOverall_rating() {
    return overall_rating;
  }

  public void setOverall_rating(String overall_rating) {
    this.overall_rating = overall_rating;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return retail_price == product.retail_price && discounted_price == product.discounted_price && product_rating == product.product_rating && overall_rating == product.overall_rating && Objects.equals(product_id, product.product_id) && Objects.equals(product_url, product.product_url) && Objects.equals(product_name, product.product_name) && Objects.equals(product_category_tree, product.product_category_tree) && Objects.equals(pid, product.pid) && Objects.equals(image, product.image) && Objects.equals(description, product.description) && Objects.equals(brand, product.brand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product_id, product_url, product_name, product_category_tree, pid, retail_price, discounted_price, image, description, product_rating, overall_rating, brand);
  }
}
