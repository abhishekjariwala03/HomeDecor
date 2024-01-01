/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package Beans;

import Entity.Addressmaster;
import Entity.Cart;
import Entity.Feedback;
import Entity.Orderdetail;
import Entity.Ordermaster;
import Entity.Products;
import Entity.Userregister;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

@Local
public interface CustomerBeanLocal {
    public void registerCustomer(String email,String username,String password,int gid,int phone,String city,String state,int pincode);
    void addAddress(String email,String city,String state,int pinCode);
    public void addUser(String email,String username,String password,int gid,int phone);
    //void loginUser(String email,String password);
    //void logoutUser();
    public void updateProfile(String email,String username,String password,int phone); // Update User
    public void manageAddress(int addressid,String email,String city,String state,int pincode); // Update Address
    public Collection<Addressmaster> getAllAddressesOfUser(String email);
    public void changePassword(String email,String password);
    
    
//    =========================Product Methods
    public Collection<Products> getAllProducts();
    public Products getProductDetails(int productid);
    public Collection<Products> getProductsByCatgegory(int categoryid);
    
    
//    ===========================Feedback Methods
    public void submitReview(String email,int productid,String review);
    public Collection<Feedback> getAllFeedbacks(int productid);
    public Collection<Feedback> getAllFeedbacksCollection();
    
//    ===============================Contact Us MEthods
    public void contactUs(String email,String query);
    
//    ================================Add To Cart Methods
    public void addProductToCart(String email,int productid,int quantity);
    public void removeProductFromCart(String email,int productid);
    public Collection<Cart> viewCart(String email);
    
//    =================================Payment Methods
    public void addPayment(int orderId, String email, String paymentMethod);
    public void addPaymentNew(String userEmail, String paymentMethod);
    public List<Integer> findOrderIdsByUserEmail(String userEmail);
    
//    ================================Order Master
    public Collection<Ordermaster> viewUserOrder(String email);
//    public void updateOrderMaster(int orderid,String email,String status);
     public void placeOrder(String userEmail,int productid);
     public Collection<Orderdetail> getOrderdetails(String email);
//     public void checkout(String userEmail);

    
//    Add to Cart
    Userregister findemail(String email);
    Cart addToCart(Cart cart,String email,int productid,int qty);
    public void clearCart(String userEmail);
}
