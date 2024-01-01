/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RestAdmin [RestAdmin]<br>
 * USAGE:
 * <pre>
 *        RestAdmin client = new RestAdmin();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author This PC
 */
public class RestAdmin {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HomeDecor_New/resources";

    public RestAdmin() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("RestAdmin");
    }

    public <T> T getAllContactuses(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllQueriesOfCustomers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllProductcategorys(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllCategories");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addProduct(String productName, String price, String quantity, String tax, String gst, String ctategoryid, String description, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addProducts/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{productName, price, quantity, tax, gst, ctategoryid, description, image})).request().post(null);
    }

    public void updateProduct(String productid, String productName, String price, String quantity, String tax, String gst, String categoryid, String discription, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateProduct/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{productid, productName, price, quantity, tax, gst, categoryid, discription, image})).request().post(null);
    }

    public <T> T viewAllOrders(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllOrders");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllPayments(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllPayments");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllUserRegister(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateCategory(String categoryid, String categoryName) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCategory/{0}/{1}", new Object[]{categoryid, categoryName})).request().post(null);
    }

    public void updatePaymentStatus(String paymentid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePaymentStatus/{0}/{1}", new Object[]{paymentid, status})).request().post(null);
    }

    public void deleteCategory(String categoryid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteCategory/{0}", new Object[]{categoryid})).request().delete();
    }

    public void updateOrderStatus(String orderid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateOrderStatus/{0}/{1}", new Object[]{orderid, status})).request().post(null);
    }

    public void addCategory(String categoryname) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addCategory/{0}", new Object[]{categoryname})).request().post(null);
    }

    public <T> T getAllProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllProducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String getOrderStatus(String orderid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderStatus/{0}", new Object[]{orderid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void deleteProduct(String productid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteProduct/{0}", new Object[]{productid})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
