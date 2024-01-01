/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package Rest;

import Beans.AdminBeanLocal;
import Entity.Contactus;
import Entity.Ordermaster;
import Entity.Payment;
import Entity.Productcategory;
import Entity.Products;
import Entity.Userregister;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author This PC
 */
@Path("RestAdmin")
@RequestScoped
public class RestAdmin {

    @Context
    private UriInfo context;

    @EJB
    AdminBeanLocal abl;

    /**
     * Creates a new instance of RestAdmin
     */
    public RestAdmin() {
    }

    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Userregister> getAllUserRegister() {
        return abl.getAllUserRegister();
    }

    @GET
    @Path("getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Products> getAllProducts() {
        return abl.getAllProducts();
    }

    @POST
    @Path("addProducts/{productName}/{price}/{quantity}/{tax}/{gst}/{ctategoryid}/{description}/{image}")
    public void addProduct(@PathParam("productName") String productName, @PathParam("price") Double price, @PathParam("quantity") int quantity, @PathParam("tax") Double tax, @PathParam("gst") Double gst, @PathParam("ctategoryid") int categoryId, @PathParam("description") String description, @PathParam("image") String image) {
        abl.addProduct(productName, price, quantity, tax, gst, categoryId, description, image);
    }

    @DELETE
    @Path("deleteProduct/{productid}")
    public void deleteProduct(@PathParam("productid") int productid) {
        abl.deleteProduct(productid);
    }

    @POST
    @Path("updateProduct/{productid}/{productName}/{price}/{quantity}/{tax}/{gst}/{categoryid}/{discription}/{image}")
    public void updateProduct(@PathParam("productid") int productid, @PathParam("productName") String productName, @PathParam("price") Double price, @PathParam("quantity") int quantity, @PathParam("tax") Double tax, @PathParam("gst") Double gst, @PathParam("categoryid") int categoryid, @PathParam("discription") String discription, @PathParam("image") String image) {
        abl.updateProduct(productid, productName, price, quantity, tax, gst, categoryid, discription, image);
    }

    @POST
    @Path("addCategory/{categoryname}")
    public void addCategory(@PathParam("categoryname") String categoryName) {
        abl.addCategory(categoryName);
    }

    @GET
    @Path("getAllCategories")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Productcategory> getAllProductcategorys() {
        return abl.getAllProductcategorys();
    }

    @DELETE
    @Path("deleteCategory/{categoryid}")
    public void deleteCategory(@PathParam("categoryid") int categoryid) {
        abl.deleteCategory(categoryid);
    }

    @POST
    @Path("updateCategory/{categoryid}/{categoryName}")
    public void updateCategory(@PathParam("categoryid") int categoryid, @PathParam("categoryName") String categoryName) {
        abl.updateCategory(categoryid, categoryName);
    }

    @GET
    @Path("getAllQueriesOfCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Contactus> getAllContactuses() {
        return abl.getAllContactuses();
    }

    @POST
    @Path("updatePaymentStatus/{paymentid}/{status}")
    public void updatePaymentStatus(@PathParam("paymentid") int paymentId, @PathParam("status") String newStatus) {
        abl.updatePaymentStatus(paymentId, newStatus);
    }

    @GET
    @Path("getAllOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Ordermaster> viewAllOrders() {
        return abl.viewAllOrders();
    }

    @POST
    @Path("updateOrderStatus/{orderid}/{status}")
    public void updateOrderStatus(@PathParam("orderid") int orderid, @PathParam("status") String status) {
        abl.updateOrderStatus(orderid, status);
    }

    @GET
    @Path("getAllPayments")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Payment> getAllPayments() {
        return abl.getAllPayments();
    }

    @GET
    @Path("getOrderStatus/{orderid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrderStatus(@PathParam("orderid") int orderId) {
        return abl.getOrderStatus(orderId);
    }

    
    @POST
    @Path("updateStatusToShipped/{orderid}/{status}")
    public void updateStatusToOrderShipped(@PathParam("orderid") int orderid,@PathParam("status") String status) {
    abl.updateStatusToOrderShipped(orderid, status);
    }
    
     @POST
    @Path("updateStatusToConfirm/{orderid}/{status}")
    public void updateStatusToOrderConfirmed(@PathParam("orderid") int orderid,@PathParam("status") String status) {
    abl.updateStatusToOrderConfirmed(orderid, status);
    }
    
     @POST
    @Path("updateStatusToDelivery/{orderid}/{status}")
    public void updateStatusToOrderOutForDelivery(@PathParam("orderid") int orderid,@PathParam("status") String status) {
    abl.updateStatusToOrderOutForDelivery(orderid, status);
    }
    
    @POST
    @Path("updateStatusToDelivered/{orderid}/{status}")
    public void updateStatusToOrderDelivered(@PathParam("orderid") int orderid,@PathParam("status") String status) {
    abl.updateStatusToOrderDelivered(orderid, status);
    }
}
