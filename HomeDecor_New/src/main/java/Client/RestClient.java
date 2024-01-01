/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RestApp1 [RestApp1]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author This PC
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/HomeDecor_New/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("RestApp1");
    }

    public void contactUs(String email, String query) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("ContactUs/{0}/{1}", new Object[]{email, query})).request().post(null);
    }

    public void removeProductFromCart(String email, String productid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeProduct/{0}/{1}", new Object[]{email, productid})).request().delete();
    }

    public <T> T getAllFeedbacksCollection(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllFeebacks");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateProfile(String email, String username, String password, String phone) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateProfile/{0}/{1}/{2}/{3}", new Object[]{email, username, password, phone})).request().post(null);
    }

    public void placeOrder(String email, String productid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("placeOrder/{0}/{1}", new Object[]{email, productid})).request().post(null);
    }

    public void submitReview(String email, String productid, String review) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addReview/{0}/{1}/{2}", new Object[]{email, productid, review})).request().post(null);
    }

    public void addUser(String email, String username, String password, String gid, String phone) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addUser/{0}/{1}/{2}/{3}/{4}", new Object[]{email, username, password, gid, phone})).request().post(null);
    }

    public void addAddress(String email, String city, String state, String pincode) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addAddress/{0}/{1}/{2}/{3}", new Object[]{email, city, state, pincode})).request().post(null);
    }

    public void addProductToCart(String email, String productid, String quantity) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addToCart/{0}/{1}/{2}", new Object[]{email, productid, quantity})).request().post(null);
    }

    public void manageAddress(String addressid, String email, String city, String state, String pincode) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateAddress/{0}/{1}/{2}/{3}/{4}", new Object[]{addressid, email, city, state, pincode})).request().post(null);
    }

    public void changePassword(String email, String password) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatePassword/{0}/{1}", new Object[]{email, password})).request().post(null);
    }

    public <T> T getAllFeedbacks(Class<T> responseType, String productid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllFeedbacks/{0}", new Object[]{productid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllProducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T viewUserOrder(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserOrders/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderdetails(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderDetails/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findemail(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("findEmail/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addToCart(Object requestEntity, Class<T> responseType, String qty) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("add/{0}", new Object[]{qty})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T viewCart(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("viewCart/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void clearCart(String email) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("clearCart/{0}", new Object[]{email})).request().delete();
    }

    public <T> T getProductsByCatgegory(Class<T> responseType, String categoryid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductsByCategoryId/{0}", new Object[]{categoryid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addPayment(String orderid, String email, String paymentmethod) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addPayment/{0}/{1}/{2}", new Object[]{orderid, email, paymentmethod})).request().post(null);
    }

    public void registerCustomer(String email, String username, String password, String gid, String phone, String city, String state, String pincode) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{email, username, password, gid, phone, city, state, pincode})).request().post(null);
    }

    public <T> T getAllAddressesOfUser(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllAddresses/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProductDetails(Class<T> responseType, String productid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductDetails/{0}", new Object[]{productid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
