/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jsfBeans;

import Beans.CustomerBeanLocal;
import Client.RestClient;
import Entity.Addressmaster;
import Entity.Cart;
import Entity.Feedback;
import Entity.Orderdetail;
import Entity.Ordermaster;
import Entity.Products;
import Entity.Userregister;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

@Named(value = "customerBean")
@SessionScoped
public class CustomerBean implements Serializable {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    @Inject
    private CustomerBeanLocal cbl;

    RestClient rc = new RestClient();
    Response r;

    String name, email, password, city, state, selectedPaymentMethod;
    int gid, pincode, phone, orderid;

    //Products
    Products product;
    Collection<Products> allproducts;
    GenericType<Collection<Products>> gallproducts;
    int qty;
    String productname, productid, quantity, tax, gst, categoryid, description, image,query;
    double price;
    private String inputQuantity;
    private int selectedCategoryId;
    Collection<Products> allproductsByCategory;
    GenericType<Collection<Products>> gallproductsByCategory;
    RestClient rs = new RestClient();

    // Cart
    Cart newCart = new Cart();
    Collection<Cart> getCart;
    GenericType<Collection<Cart>> ggetCart;
    private List<Cart> cartItem;

    //Address
    Collection<Addressmaster> address;
    GenericType<Collection<Addressmaster>> gaddress;

    //Orders
    Collection<Orderdetail> orderDetails;
    GenericType<Collection<Orderdetail>> gorderDetails;
    Collection<Ordermaster> ordermaster;
    GenericType<Collection<Ordermaster>> gordermaster;

    //Review
    Collection<Feedback> AllFeedbacks;
    GenericType<Collection<Feedback>> gAllFeedbacks;
    String addUserReview;

    private Collection<Cart> cartItems = new ArrayList<>();

    //User Session
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    HttpSession session = (HttpSession) externalContext.getSession(false);
    String userEmail = (String) session.getAttribute("user");

    public CustomerBean() {
        gid = 2;
        rs = new RestClient();

        allproducts = new ArrayList<>();
        gallproducts = new GenericType<Collection<Products>>() {
        };

        allproductsByCategory = new ArrayList<>();
        gallproductsByCategory = new GenericType<Collection<Products>>() {
        };

        getCart = new ArrayList<>();
        ggetCart = new GenericType<Collection<Cart>>() {
        };

        address = new ArrayList<>();
        gaddress = new GenericType<Collection<Addressmaster>>() {
        };

        orderDetails = new ArrayList<>();
        gorderDetails = new GenericType<Collection<Orderdetail>>() {
        };

        ordermaster = new ArrayList<>();
        gordermaster = new GenericType<Collection<Ordermaster>>() {
        };

        AllFeedbacks = new ArrayList<>();
        gAllFeedbacks = new GenericType<Collection<Feedback>>() {
        };
    }

    public Collection<Products> getAllProducts() {
        try {
            r = rc.getAllProducts(Response.class);
            allproducts = r.readEntity(gallproducts);
            return allproducts;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String RegisterCustomer() {
        System.out.println(name);
        System.out.println(email);
        System.out.println(password);
        System.out.println(gid);
        System.out.println(phone);
        System.out.println(city);
        System.out.println(state);
        System.out.println(pincode);
        cbl.registerCustomer(email, name, password, gid, phone, city, state, pincode);
        return "Login.jsf?faces-redirect=true";
    }

    public void loadItemDetails() {
        if (productid != null && !productid.isEmpty() && em != null) {
            try {
                // Assuming you have an Entity named 'Item' with a property 'itemName'
                TypedQuery<Products> query = em.createNamedQuery("Products.findByProductid", Products.class);
                query.setParameter("productid", Integer.valueOf(productid));

                Products item = query.getSingleResult();
                if (item != null) {
                    productname = item.getProductname();
                    price = item.getPrice();
                    description = item.getDescription();
                    image = item.getImage();
                    System.out.println(image);
                } else {
                    productname = "Item Not Found";
                }
            } catch (NumberFormatException | javax.persistence.NoResultException e) {
                // Handle exception or set a default value for itemName if parsing or query fails
                productname = "Item Not Found";
            }
        }
    }

    public List<Feedback> getAllReview(int productid) {
        try {
            Products product = em.find(Products.class, productid);

            if (product != null) {

                List<Feedback> feedbacks = new ArrayList<>(product.getFeedbackCollection());
                return feedbacks;
            } else {
                System.err.println("Product Id is null for getAllReview");
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Collection<Products> getAllProductsByCategory() {
        try {
            if (selectedCategoryId > 0) {
                r = rc.getProductsByCatgegory(Response.class, String.valueOf(selectedCategoryId));
                allproductsByCategory = r.readEntity(gallproductsByCategory);
                return allproductsByCategory;
            } else {
                // Handle case when selectedCategoryId is not set
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addToCart() {
        Products p = rc.getProductDetails(Products.class, productid);
        Userregister u = rc.findemail(Userregister.class, userEmail);
        newCart.setProductid(p);
        newCart.setUserregister(u);
        newCart.setQuantity(qty);
        rc.addToCart(newCart, Cart.class, qty + "");
        System.out.println("Email:" + userEmail);
        System.out.println("ProductId" + productid);
        System.out.println("Quantity:" + quantity);
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Invalidate the session
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate();
            return "Login.jsf?faces-redirect=true";
        }
        return "Login.jsf?faces-redirect=true";
    }

    public Collection<Cart> getCartItems() {
        if (userEmail != null) {
            cartItems = rc.viewCart(Collection.class, userEmail);
            return cartItems;
        } else {
            return null;
        }
    }

    public String removeProductFromCart(int productid) {
        System.out.println(productid);
        rc.removeProductFromCart(userEmail, productid + "");
        return "add.jsf?faces-redirect=true";
    }

    public Collection<Addressmaster> getAllAddresses() {
        try {
            r = rc.getAllAddressesOfUser(Response.class, userEmail);
            address = r.readEntity(gaddress);
            return address;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String checkEmail() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        email = (String) session.getAttribute("admin");
        if (email == null) {
            // Redirect to the login page
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/AdminPage.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/Login.jsf?faces-redirect=true";
        }
        return "successOutcome";
    }

    public String checkout(int productId) {
        rc.placeOrder(userEmail, productId + "");
//          rc.clearCart(userEmail);
        return "Check.jsf?faces-redirect=true";
    }

    public Collection<Orderdetail> allProductDetails() {
        try {
            r = rc.getOrderdetails(Response.class, userEmail);
            orderDetails = r.readEntity(gorderDetails);
            return orderDetails;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Collection<Ordermaster> AllOrderMaster() {
        try {
            r = rc.viewUserOrder(Response.class, userEmail);
            ordermaster = r.readEntity(gordermaster);
            return ordermaster;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Collection<Feedback> getReview() {
        if (productid != null) {
            r = rc.getAllFeedbacks(Response.class, productid);
            AllFeedbacks = r.readEntity(gAllFeedbacks);
            return AllFeedbacks;
        } else {
            return null;
        }
    }

    public String AddReview() {
        
        //User Session
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    HttpSession session = (HttpSession) externalContext.getSession(false);
    String userEmail = (String) session.getAttribute("user");
        
        System.out.println("review email : " + email);
        System.out.println("review email : " + productid);
         System.out.println("review email : " + addUserReview);
                
        rc.submitReview(userEmail, productid, addUserReview);
        return "Productdetail.jsf?faces-redirect=true";

    }
    
    public String addQuery(){
        System.out.println("Email contact :" + userEmail);
        System.out.println("Query : " + query);
        rc.contactUs(userEmail, query);
        return "";
    }
    
    public Collection<Feedback> getAllFeedbackCollection(){
        try{
            r = rc.getAllFeedbacksCollection(Response.class);
            AllFeedbacks = r.readEntity(gAllFeedbacks);
            return AllFeedbacks;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void placeOrder() {
        if (null != selectedPaymentMethod) {
            switch (selectedPaymentMethod) {
                case "bankTransfer":
                    selectedPaymentMethod = "bankTransfer";
                    break;
                case "checkPayments":
                    selectedPaymentMethod = "checkPayments";
                    break;
                case "cashOnDelivery":
                    selectedPaymentMethod = "cashOnDelivery";
                    break;
                case "paypal":
                    selectedPaymentMethod = "paypal";
                    break;
                default:
                    break;
            }

            cbl.addPaymentNew(userEmail, selectedPaymentMethod);
        }
        rc.addPayment(orderid + "", userEmail, selectedPaymentMethod);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public RestClient getRs() {
        return rs;
    }

    public void setRs(RestClient rs) {
        this.rs = rs;
    }

    public CustomerBeanLocal getCbl() {
        return cbl;
    }

    public void setCbl(CustomerBeanLocal cbl) {
        this.cbl = cbl;
    }

    public RestClient getRc() {
        return rc;
    }

    public void setRc(RestClient rc) {
        this.rc = rc;
    }

    public Response getR() {
        return r;
    }

    public void setR(Response r) {
        this.r = r;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Collection<Products> getAllproducts() {
        return allproducts;
    }

    public void setAllproducts(Collection<Products> allproducts) {
        this.allproducts = allproducts;
    }

    public GenericType<Collection<Products>> getGallproducts() {
        return gallproducts;
    }

    public void setGallproducts(GenericType<Collection<Products>> gallproducts) {
        this.gallproducts = gallproducts;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(int selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public String getInputQuantity() {
        return inputQuantity;
    }

    public void setInputQuantity(String inputQuantity) {
        this.inputQuantity = inputQuantity;
    }

    public Collection<Products> getAllproductsByCategory() {
        return allproductsByCategory;
    }

    public void setAllproductsByCategory(Collection<Products> allproductsByCategory) {
        this.allproductsByCategory = allproductsByCategory;
    }

    public GenericType<Collection<Products>> getGallproductsByCategory() {
        return gallproductsByCategory;
    }

    public void setGallproductsByCategory(GenericType<Collection<Products>> gallproductsByCategory) {
        this.gallproductsByCategory = gallproductsByCategory;
    }

    public Cart getNewCart() {
        return newCart;
    }

    public void setNewCart(Cart newCart) {
        this.newCart = newCart;
    }

    public Collection<Cart> getGetCart() {
        return getCart;
    }

    public void setGetCart(Collection<Cart> getCart) {
        this.getCart = getCart;
    }

    public GenericType<Collection<Cart>> getGgetCart() {
        return ggetCart;
    }

    public void setGgetCart(GenericType<Collection<Cart>> ggetCart) {
        this.ggetCart = ggetCart;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Collection<Addressmaster> getAddress() {
        return address;
    }

    public void setAddress(Collection<Addressmaster> address) {
        this.address = address;
    }

    public GenericType<Collection<Addressmaster>> getGaddress() {
        return gaddress;
    }

    public void setGaddress(GenericType<Collection<Addressmaster>> gaddress) {
        this.gaddress = gaddress;
    }

    public List<Cart> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<Cart> cartItem) {
        this.cartItem = cartItem;
    }

    public Collection<Orderdetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<Orderdetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public GenericType<Collection<Orderdetail>> getGorderDetails() {
        return gorderDetails;
    }

    public void setGorderDetails(GenericType<Collection<Orderdetail>> gorderDetails) {
        this.gorderDetails = gorderDetails;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    public ExternalContext getExternalContext() {
        return externalContext;
    }

    public void setExternalContext(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Collection<Ordermaster> getOrdermaster() {
        return ordermaster;
    }

    public void setOrdermaster(Collection<Ordermaster> ordermaster) {
        this.ordermaster = ordermaster;
    }

    public GenericType<Collection<Ordermaster>> getGordermaster() {
        return gordermaster;
    }

    public void setGordermaster(GenericType<Collection<Ordermaster>> gordermaster) {
        this.gordermaster = gordermaster;
    }

    public String getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(String selectedPaymentMethod) {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Collection<Feedback> getAllFeedbacks() {
        return AllFeedbacks;
    }

    public void setAllFeedbacks(Collection<Feedback> AllFeedbacks) {
        this.AllFeedbacks = AllFeedbacks;
    }

    public GenericType<Collection<Feedback>> getgAllFeedbacks() {
        return gAllFeedbacks;
    }

    public void setgAllFeedbacks(GenericType<Collection<Feedback>> gAllFeedbacks) {
        this.gAllFeedbacks = gAllFeedbacks;
    }

    public String getAddUserReview() {
        return addUserReview;
    }

    public void setAddUserReview(String addUserReview) {
        this.addUserReview = addUserReview;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
