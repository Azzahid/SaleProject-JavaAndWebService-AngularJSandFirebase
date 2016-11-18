/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marketplace;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Silva
 */
@WebService(serviceName = "marketplace")
public class marketplace {

    /**
     * This is a sample web service operation
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param id
     * @param productid
     * @return 
     */
    @WebMethod(operationName = "getLike")
    public int getLike(@WebParam(name = "productid") int productid) {
        //TODO write your implementation code here:
        DbConnector connect = new DbConnector();
        int like = connect.getTotalLike(productid);
        connect.close();
        return like;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAllProduct")
    public Product[] getAllProduct() {
        Product[] result = null;
        DbConnector con = new DbConnector();
        if((result = con.getProduct())!=null){
            return result;
        }else{
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLikeStatus")
    public Integer getLikeStatus(@WebParam(name = "productid") int productid, @WebParam(name = "userid") int userid) {
        Integer result = null;
        DbConnector con = new DbConnector();
        result = con.getLikeStatus(productid, userid);
        con.close();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTotalPurchase")
    public Integer getTotalPurchase(@WebParam(name = "id") int id) {
        Integer result = null;
        DbConnector con = new DbConnector();
        result = con.getTotalPurchase(id);
        con.close();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "searchProduct")
    public Product[] searchProduct(@WebParam(name = "text") String text, @WebParam(name = "option") int option) {
        Product[] result = null;
        DbConnector con = new DbConnector();
        result = con.searchProduct(text,option);
        con.close();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProductPurchase")
    public Purchase[] getProductPurchase(@WebParam(name = "userid") int userid) {
        Purchase[] result;
        DbConnector con = new DbConnector();
        result =con.getProductPurchase(userid, 1);
        con.close();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProductSales")
    public Purchase[] getProductSales(@WebParam(name = "userid") int userid) {
        Purchase[] result;
        DbConnector con = new DbConnector();
        result = con.getProductPurchase(userid, 0);
        con.close();
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPhoto")
    public Product getPhoto(@WebParam(name = "id") int id) {
        Product photo;
        DbConnector con = new DbConnector();
        photo = con.getPhotoProduct(id);
        con.close();
        return photo;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getPhotoPurchase")
    public Purchase getPhotoPurchase(@WebParam(name = "id") int id) {
        Purchase photo;
        DbConnector con = new DbConnector();
        photo = con.getPhotoPurchase(id);
        con.close();
        return photo;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "confirmPurchase")
    public Boolean confirmPurchase(@WebParam(name = "buyer_id") int buyer_id,
            @WebParam(name = "product_id") int product_id,
            @WebParam(name = "consignee") String consignee,
            @WebParam(name = "fulladdress") String fulladdress,
            @WebParam(name = "quantity") int quantity,
            @WebParam(name = "creditcardnumber") String creditcardnumber,
            @WebParam(name = "postalcode") String postalcode,
            @WebParam(name = "phonenumber") String phonenumber,
            @WebParam(name = "card_verification") String card_verification            
            ) {
        //TODO write your implementation code here:
        DbConnector con = new DbConnector();
        Boolean x = false;
        try {
            x = con.confirmPurchase(buyer_id,
                    product_id,
                    consignee,
                    fulladdress,
                    quantity,
                    creditcardnumber,
                    postalcode,
                    phonenumber,
                    card_verification);
        } catch (SQLException ex) {
            Logger.getLogger(marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addProduct")
    public Boolean addProduct(@WebParam(name = "productname") String productname, @WebParam(name = "description") String description, @WebParam(name = "price") String price, @WebParam(name = "imageblob") String imageblob, @WebParam(name = "userid") int userid,
                                  @WebParam(name = "image_type") String image_type, @WebParam(name = "image_name") String image_name) {
        //TODO write your implementation code here:
        DbConnector con = new DbConnector();

        try {
            return con.addProduct(productname,description,price,imageblob,userid,image_type,image_name);
        } catch (SQLException ex) {
            Logger.getLogger(marketplace.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "insertLike")
    public Boolean insertLike(@WebParam(name = "barangid") int barangid, @WebParam(name = "userid") int userid) {
       Boolean result = false;
       DbConnector con = new DbConnector();
       try{
            result = con.InsertLikeUser(barangid, userid);
       }catch(Exception Ex){
            result = false;
       }
       return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "changeLikeStatus")
    public Boolean changeLikeStatus(@WebParam(name = "barangid") int barangid, @WebParam(name = "userid") int userid) {
        Boolean result = false;
        DbConnector con = new DbConnector();
        try{
            int x = con.getLikeStatus(barangid, userid);
            result = con.changeLikeStatus(barangid, x, userid);
        }catch(Exception ex){
            result = false;
            System.out.println("Error : "+ex);
        }
        return result;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getYourProduct")
    public Product[] getYourProduct(@WebParam(name = "user_id") int user_id) {
        Product[] result = null;
        DbConnector con = new DbConnector();
        if((result = con.getYourProduct(user_id))!=null){
            return result;
        }else{
            return null;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteProduct")
    public Boolean deleteProduct(@WebParam(name = "product_id") int product_id) {
        DbConnector con = new DbConnector();
        return con.deleteProduct(product_id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editProduct")
    public Boolean editProduct(@WebParam(name = "productName") String productName, 
            @WebParam(name = "productPrice") String productPrice, 
            @WebParam(name = "productDescription") String productDescription,
            @WebParam(name = "product_id") int product_id) {
        DbConnector con = new DbConnector();
        return con.editProduct(productName, productPrice, productDescription, product_id);
    }
}
