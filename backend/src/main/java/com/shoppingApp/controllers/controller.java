package com.shoppingApp.controllers;

import com.shoppingApp.model.dao.ProductDao;
import com.shoppingApp.model.dao.UserDao;
import com.shoppingApp.model.dto.Product;
import com.shoppingApp.model.dto.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class controller {
  @Autowired
  ProductDao productDao;

  @Autowired
  UserDao userDao;

  //get all products
  @GetMapping("allProducts")
  public String displayProducts(Model model) {
    List<Product> products=productDao.getAllProducts();
    model.addAttribute("products", products);
    return "products";
  }
  //get cart for user
  @GetMapping("cart")
  public String getCartForUser(Integer id, Model model) {
    User user=userDao.getUserById(id.intValue());
    model.addAttribute("user", user);
    model.addAttribute("cart", user.getCart());
    return "cart";
  }
  //add product to cart
  @PostMapping("addProductToCart")
  public String addProductToCart(HttpServletRequest req){
    String pid = req.getParameter("product_id");
    Product product=productDao.getProductById(pid);
    String uid = req.getParameter("user_id");
    int user_id = Integer.parseInt(uid);

    productDao.addProductToCart(product, user_id);
    return "redirect:/products";
  }
  //delete product from cart
  @PostMapping("removeProductFromCart")
  public String removeProductToCart(HttpServletRequest req){
    String pid = req.getParameter("product_id");
    Product product=productDao.getProductById(pid);
    String uid = req.getParameter("user_id");
    int user_id = Integer.parseInt(uid);

    productDao.deleteProductFromCart(product, user_id);
    return "redirect:/products";
  }
}
