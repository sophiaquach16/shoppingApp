package com.shoppingApp.controllers;

public class ShoppingDataValidationError extends Exception {

  public ShoppingDataValidationError(String message, Throwable cause) {
    super(message, cause);
  }

  public ShoppingDataValidationError(String message) {
  }
}
