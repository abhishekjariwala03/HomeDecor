/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package Rest;

import Beans.CustomerBeanLocal;
import Entity.Addressmaster;
import Entity.Cart;
import Entity.Feedback;
import Entity.Orderdetail;
import Entity.Ordermaster;
import Entity.Products;
import Entity.Userregister;
import java.util.Collection;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author This PC
 */
@Path("RestApp1")
@RequestScoped
public class RestApp1 {

    @EJB
    CustomerBeanLocal cbl;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestApp1
     */
    public RestApp1() {
    }

    @POST
    @Path("registerUser/{email}/{username}/{password}/{gid}/{phone}/{city}/{state}/{pincode}")
    public void registerCustomer(@PathParam("email") String email, @PathParam("username") String username, @PathParam("password") String password, @PathParam("gid") int gid, @PathParam("phone") int phone, @PathParam("city") String city, @PathParam("state") String state, @PathParam("pincode") int pincode) {
        cbl.registerCustomer(email, username, password, gid, phone, city, state, pincode);
    }

    @POST
    @Path("updateProfile/{email}/{username}/{password}/{phone}")
    public void updateProfile(@PathParam("email") String email, @PathParam("username") String username, @PathParam("password") String password, @PathParam("phone") int phone) {
        cbl.updateProfile(email, username, password, phone);
    }

    @POST
    @Path("updateAddress/{addressid}/{email}/{city}/{state}/{pincode}")
    public void manageAddress(@PathParam("addressid") int addressid, @PathParam("email") String email, @PathParam("city") String city, @PathParam("state") String state, @PathParam("pincode") int pincode) {
        cbl.manageAddress(addressid, email, city, state, pincode);
    }

    @POST
    @Path("addAddress/{email}/{city}/{state}/{pincode}")
    public void addAddress(@PathParam("email") String email, @PathParam("city") String city, @PathParam("state") String state, @PathParam("pincode") int pinCode) {
        cbl.addAddress(email, city, state, pinCode);
    }

    @GET
    @Path("getAllAddresses/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Addressmaster> getAllAddressesOfUser(@PathParam("email") String email) {
        return cbl.getAllAddressesOfUser(email);
    }

    @GET
    @Path("getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Products> getAllProducts() {
        return cbl.getAllProducts();
    }

    @GET
    @Path("getProductDetails/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getProductDetails(@PathParam("productid") int productid) {
        return cbl.getProductDetails(productid);
    }

    @GET
    @Path("getProductsByCategoryId/{categoryid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Products> getProductsByCatgegory(@PathParam("categoryid") int categoryid) {
        return cbl.getProductsByCatgegory(categoryid);
    }

    @POST
    @Path("addReview/{email}/{productid}/{review}")
    public void submitReview(@PathParam("email") String email, @PathParam("productid") int productid, @PathParam("review") String review) {
        cbl.submitReview(email, productid, review);
    }

    @GET
    @Path("getAllFeedbacks/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Feedback> getAllFeedbacks(@PathParam("productid") int productid) {
        return cbl.getAllFeedbacks(productid);
    }

    @POST
    @Path("ContactUs/{email}/{query}")
    public void contactUs(@PathParam("email") String email, @PathParam("query") String query) {
        cbl.contactUs(email, query);
    }

    @POST
    @Path("addToCart/{email}/{productid}/{quantity}")
    public void addProductToCart(@PathParam("email") String email, @PathParam("productid") int productid, @PathParam("quantity") int quantity) {
        cbl.addProductToCart(email, productid, quantity);
    }

    @DELETE
    @Path("removeProduct/{email}/{productid}")
    public void removeProductFromCart(@PathParam("email") String email, @PathParam("productid") int productid) {
        cbl.removeProductFromCart(email, productid);
    }

    @GET
    @Path("viewCart/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Cart> viewCart(@PathParam("email") String email) {
        return cbl.viewCart(email);
    }

    @POST
    @Path("addPayment/{orderid}/{email}/{paymentmethod}")
    public void addPayment(@PathParam("orderid") int orderId, @PathParam("email") String email, @PathParam("paymentmethod") String paymentMethod) {
        cbl.addPayment(orderId, email, paymentMethod);
    }

    @GET
    @Path("getUserOrders/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Ordermaster> viewUserOrder(@PathParam("email") String email) {
        return cbl.viewUserOrder(email);
    }

    @POST
    @Path("updatePassword/{email}/{password}")
    public void changePassword(@PathParam("email") String email, @PathParam("password") String password) {
        cbl.changePassword(email, password);
    }

    @POST
    @Path("addUser/{email}/{username}/{password}/{gid}/{phone}")
    public void addUser(@PathParam("email") String email, @PathParam("username") String username, @PathParam("password") String password, @PathParam("gid") int gid, @PathParam("phone") int phone) {
        cbl.addUser(email, username, password, gid, phone);
    }

    @GET
    @Path("findEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Userregister findemail(@PathParam("email") String email) {
        return cbl.findemail(email);
    }

    @POST
    @Path("/add/{qty}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cart addToCart(Cart cart, @PathParam("qty") int qty) {
        return cbl.addToCart(cart, cart.getUserregister().getEmail(), cart.getProductid().getProductid(), qty);
    }

    @DELETE
    @Path("clearCart/{email}")
    public void clearCart(@PathParam("email") String userEmail) {
        cbl.clearCart(userEmail);
    }

    @POST
    @Path("placeOrder/{email}/{productid}")
    public void placeOrder(@PathParam("email") String userEmail, @PathParam("productid") int productid) {
        cbl.placeOrder(userEmail, productid);
    }

    @GET
    @Path("getOrderDetails/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Orderdetail> getOrderdetails(@PathParam("email") String email) {
        return cbl.getOrderdetails(email);
    }

    @GET
    @Path("getAllFeebacks")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Feedback> getAllFeedbacksCollection() {
        return cbl.getAllFeedbacksCollection();
    }

}
