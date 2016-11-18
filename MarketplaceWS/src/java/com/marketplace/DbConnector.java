/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marketplace;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Silva
 */
public class DbConnector {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DbConnector(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/t2_product", "root", "");
            st = (Statement) con.createStatement();
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error "+ ex);
        }
    }
    
    public int getTotalLike(int productid){
        int count = -999;
        try{
            String query;
            query = ("SELECT COUNT(*) AS total FROM user_like WHERE barang_id='"+Integer.toString(productid)+"' AND status !=0");
            rs = st.executeQuery(query);
            while(rs.next()){
                count = rs.getInt("total");
            }
        } catch (Exception ex) {
            System.out.println("Result: "+ex);
        }
        return count;
    }
    
    public Product[] getProduct(){
        Product[] es = null;
        try{
            String query;
            int count;
            int i = 0;
            query = ("SELECT COUNT(*) AS total FROM product");
            rs = st.executeQuery(query);
            if(rs.next()){
                count = rs.getInt("total");
            }else{
                return null;
            }
            if(count==0){
                return null;
            }
            es = new Product[count];
            query = ("SELECT * FROM product");
            rs = st.executeQuery(query);
            while(rs.next()){
                /*
                private Integer pId;
                private String namaProduk;
                private String description;
                private String price;
                private byte[] photoUrl;
                private Date createdAt;
                private String imageType;
                private String imageName;
                private User userId;*/
                es[i] = new Product();
                es[i].setPId(rs.getInt("p_Id"));
                es[i].setNamaProduk(rs.getString("namaProduk"));
                es[i].setDescription(rs.getString("description"));
                es[i].setPrice(rs.getString("price"));
                es[i].setPhotoUrl(null);
                es[i].setCreatedAt(rs.getTimestamp("created_at"));
                es[i].setImageType(rs.getString("image_Type"));
                es[i].setImageName(rs.getString("image_Name"));
                es[i].setUserId(rs.getInt("user_Id"));
                i++;
            }
        }catch(Exception ex){
            System.out.println("Result: "+ex);
        }
        
        return es;
    }
    
    public Integer getLikeStatus(int barangid, int userid){
        Integer result = -999;
            try{
                String query;
                int i = 0;
                query = ("SELECT * FROM user_like WHERE user_id = '"+userid+"' AND barang_id = '"+barangid+"';");
                rs = st.executeQuery(query);
                if(rs.next()){
                    result = rs.getInt("status");
                }else{
                    result =  -999;
                }
            }catch(Exception ex){
                System.out.println("Result : "+ex);
            }
        
        return result;
    }
    
    public Integer getTotalPurchase(int id){
        Integer result = null;
            
            try{
                String query;
                int i = 0;
                query = ("SELECT COUNT(*) AS total FROM purchase WHERE product_id = '"+id+"'");
                rs = st.executeQuery(query);
                if(rs.next()){
                    result = rs.getInt("total");
                }else{
                    return null;
                }
            }catch(Exception ex){
                System.out.println("Result : "+ex);
            }
        
        return result;
    }
    
    public Product[] searchProduct(String text, Integer pilihan){
        Product[] es = null;
        String query;
        String query1;
        if(pilihan == 0){
            query = ("SELECT COUNT(*) AS total FROM product WHERE namaProduk LIKE '%"+text+"%'");
            query1 = ("SELECT * FROM product WHERE namaProduk LIKE '%"+text+"%' ORDER BY created_at DESC");
        }else{
            try{
                String url2 = "http://localhost:8082/IdentityServices/SearchServlet";
                URL iurl2 = new URL(url2);
                HttpURLConnection connection2 = (HttpURLConnection)iurl2.openConnection();
                connection2.setDoOutput(true);
                connection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Send POST output.
                connection2.setRequestMethod("POST");
                java.io.DataOutputStream printout2 = new java.io.DataOutputStream(connection2.getOutputStream ());
        //        out.println(token);
                String content2 = "text=" + text;
                printout2.writeBytes (content2);
                printout2.flush (); 
                printout2.close ();  

                // retrieve response from IS
                java.io.BufferedReader reader2 = new java.io.BufferedReader(new java.io.InputStreamReader(
                    (java.io.InputStream) connection2.getContent()));

                text = connection2.getHeaderField("arrayid");
            }catch(Exception ex){
                System.out.println("Error : "+ex);
            }

            query = ("SELECT COUNT(*) AS total FROM product WHERE user_id IN "+text+"");
            query1 = ("SELECT * FROM product WHERE user_id IN "+text+" ORDER BY created_at DESC");
        }
        try{
            int count;
            int i = 0;
            rs = st.executeQuery(query);
            if(rs.next()){
                count = rs.getInt("total");
            }else{
                return null;
            }
            if(count==0){
                return null;
            }
            es = new Product[count];
            rs = st.executeQuery(query1);
            while(rs.next()){
                es[i] = new Product();
                es[i].setPId(rs.getInt("p_id"));
                es[i].setNamaProduk(rs.getString("namaProduk"));
                es[i].setDescription(rs.getString("description"));
                es[i].setPrice(rs.getString("price"));
                es[i].setPhotoUrl(null);
                es[i].setCreatedAt(rs.getTimestamp("created_at"));
                es[i].setImageType(rs.getString("image_Type"));
                es[i].setImageName(rs.getString("image_Name"));
                es[i].setUserId(rs.getInt("user_Id"));
                i++;
            }
        }catch(SQLException ex){
            System.out.println("Result: "+ex);
            es = null;
        }
        
        return es;
    }
    
    public Purchase[] getProductPurchase(Integer userid, Integer pilihan){
        Purchase[] es = null;
        String query;
        String query1;
        if(pilihan  == 1){
            query = ("SELECT COUNT(*) AS total FROM purchase WHERE purchase.buyer_id = '"+userid+"'");
            query1 = ("SELECT * FROM purchase WHERE purchase.buyer_id = '"+userid+"' ORDER BY created_at DESC");
        }else{
            query = ("SELECT COUNT(*) AS total FROM purchase WHERE purchase.seller_id = '"+userid+"'");
            query1 = ("SELECT * FROM purchase WHERE purchase.seller_id = '"+userid+"' ORDER BY created_at DESC");
        }
        try{
            int count;
            int i = 0;
            rs = st.executeQuery(query);
            if(rs.next()){
                count = rs.getInt("total");
            }else{
                return null;
            }
            if(count==0){
                return null;
            }
            es = new Purchase[count];
            rs = st.executeQuery(query1);
            while (rs.next()) {
                es[i] = new Purchase();
                es[i].setPurchaseId(rs.getInt("purchase_id"));
                es[i].setBuyerId(rs.getInt("buyer_id"));
                es[i].setProductId(rs.getInt("product_id"));
                es[i].setConsignee(rs.getString("consignee"));
                es[i].setFulladdress(rs.getString("fulladdress"));
                es[i].setQuantity(rs.getInt(6));
                es[i].setCreditcardnumber(rs.getString(7));
                es[i].setPostalcode(rs.getString(8));
                es[i].setPhonenumber(rs.getString(9));
                es[i].setCreatedAt(rs.getTimestamp(10));
                es[i].setCardVerification(rs.getString(11));
                es[i].setProductName(rs.getString(12));
                es[i].setProductDescription(rs.getString(13));
                es[i].setProductPrice(rs.getString(14));
                es[i].setProductPhotourl(null);
                es[i].setSellerId(rs.getInt(16));
                es[i].setImageType(rs.getString(17));
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("Result: " + ex);
        }
        
        return es;
    }

    Product getPhotoProduct(int id) {
        Product result = null;
        String query1;
        query1 = ("SELECT * FROM product WHERE p_id = '"+id+"'");
       
        try{
            rs = st.executeQuery(query1);
            if(rs.next()){
                result= new Product();
                 result.setPId(rs.getInt("p_id"));
                result.setNamaProduk(rs.getString("namaProduk"));
                 result.setDescription(rs.getString("description"));
                result.setPrice(rs.getString("price"));
                 result.setPhotoUrl(rs.getBytes("photo_url"));
                 result.setCreatedAt(rs.getTimestamp("created_at"));
                 result.setImageType(rs.getString("image_Type"));
                result.setImageName(rs.getString("image_Name"));
                 result.setUserId(rs.getInt("user_Id"));
            }
        } catch (SQLException ex) {
            System.out.println("Result: " + ex);
        }
        return result;
    }
    
    public Purchase getPhotoPurchase(int id){
        String query1;
        query1 = ("SELECT * FROM purchase WHERE purchase.purchase_id = '"+id+"'");
        Purchase result = null;
        try{
            rs = st.executeQuery(query1);
            if (rs.next()) {
                result = new Purchase();
                result.setPurchaseId(rs.getInt("purchase_id"));
                result.setBuyerId(rs.getInt("buyer_id"));
                result.setProductId(rs.getInt("product_id"));
                result.setConsignee(rs.getString("consignee"));
                result.setFulladdress(rs.getString("fulladdress"));
                result.setQuantity(rs.getInt(6));
                result.setCreditcardnumber(rs.getString(7));
                result.setPostalcode(rs.getString(8));
                result.setPhonenumber(rs.getString(9));
                result.setCreatedAt(rs.getTimestamp(10));
                result.setCardVerification(rs.getString(11));
                result.setProductName(rs.getString(12));
                result.setProductDescription(rs.getString(13));
                result.setProductPrice(rs.getString(14));
                result.setProductPhotourl(rs.getBytes(15));
                result.setSellerId(rs.getInt(16));
                result.setImageType(rs.getString(17));
            }
        }catch(Exception ex){
            System.out.println("Error :" +ex);
        }
        return result;
    }
    
    public boolean confirmPurchase(int buyer_id, 
            int product_id, 
            String consignee,
            String fulladdress, 
            int quantity, 
            String creditcardnumber, 
            String postalcode,
            String phonenumber, 
            String card_verification
            ) throws SQLException {
        
        String query1;
        String product_name = null;
        String product_description = null;
        String product_price = null;
        Blob product_photourl = null;
        int seller_id = 0;
        String image_type = null;
        query1 = ("SELECT * FROM product WHERE p_id = '"+product_id+"'");
        try{
            rs = st.executeQuery(query1);
            if (rs.next()) {
                product_name = rs.getString("namaProduk");
                product_description = rs.getString("description");
                product_price = rs.getString("price");
                product_photourl =  rs.getBlob("photo_url");
                seller_id = rs.getInt("user_id");
                image_type = rs.getString("image_type");
            }
            
        }catch(Exception ex){
            System.out.println("Error :" +ex);
        }
        
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        PreparedStatement ps;
               
        
        String query2= null;
        query2 = "INSERT INTO purchase (buyer_id, product_id, consignee, fulladdress, "
                + "quantity, creditcardnumber, postalcode, phonenumber, created_at, card_verification, "
                + "product_name, product_description, product_price, product_photourl, seller_id, image_type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ps = con.prepareStatement(query2);
        ps.setInt(1, buyer_id);
        ps.setInt(2, product_id);
        ps.setString(3, consignee);
        ps.setString(4, fulladdress);
        ps.setInt(5, quantity);
        ps.setString(6, creditcardnumber);
        ps.setString(7, postalcode);
        ps.setString(8, phonenumber);
        ps.setTimestamp(9, now);
        ps.setString(10, card_verification);
        ps.setString(11, product_name);
        ps.setString(12, product_description);
        ps.setString(13, product_price);
        ps.setBlob(14, product_photourl);
        ps.setInt(15, seller_id);
        ps.setString(16, image_type);
        ps.execute();
        ps.close();
        
//        try {
//            st.executeUpdate(query2);
//            con.close();
//        } catch (SQLException ex) {
//            System.out.println("Error2 :" +ex);
//            return false;
//        }
        
        return true;
    }
    
    public Boolean changeLikeStatus(int barangid, int status,int userid){
        Boolean result = false;
        String query1;
        if(status == 0){
            query1 = "UPDATE user_like SET status = 1 WHERE user_id = '"+userid+"'AND barang_id='"+barangid+"'";
        }else{
            query1 = ("UPDATE user_like SET status = 0 WHERE user_id = '"+userid+"'AND barang_id='"+barangid+"'");
        }
        try{
            int k = st.executeUpdate(query1);
            result = k>0;
        } catch( Exception ex ) {
          System.out.println("Error : " + ex);
        }
        
        return result;
    }
    
    public Boolean InsertLikeUser(int barangid, int userid){
        Boolean result = false;
        String query1;
        query1 = "INSERT INTO user_like (user_id, barang_id, status) VALUES ('"+userid+"','"+barangid+"',1)";
        try{
            int k = st.executeUpdate(query1);
            result = k>0;
        } catch( Exception ex ) {
          System.out.println("Error : " + ex);
        }
        
        return result;
    }
    
    public boolean addProduct(String productname,String description,String price,String imageblob, int userid, String image_type, String image_name) throws SQLException{
        String query1;
        byte[] imageByte = Base64.getDecoder().decode(imageblob);
        Blob blob = new javax.sql.rowset.serial.SerialBlob(imageByte);
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        query1 = "INSERT INTO product(namaProduk,description,price,photo_url,created_at,user_id,image_type,image_name)" +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            /*st.executeUpdate(query1);
            con.close();*/
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setString(1, productname);
            ps.setString(2, description);
            ps.setString(3, price);
            ps.setBlob(4, blob);
            ps.setTimestamp(5, now);
            ps.setInt(6,userid);
            ps.setString(7, image_type);
            ps.setString(8, image_name);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error2 :" +ex);
            return false;
        }
        
        return true;
    }
    
    public void close(){
        try {
            rs.close();
             st.close();
             con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Product[] getYourProduct(int user_id){
        Product[] es = null;
        try{
            String query;
            int count;
            int i = 0;
            query = ("SELECT COUNT(*) AS total FROM product WHERE user_id = '"+user_id+"'");
            rs = st.executeQuery(query);
            if(rs.next()){
                count = rs.getInt("total");
            }else{
                return null;
            }
            if(count==0){
                return null;
            }
            es = new Product[count];
            query = ("SELECT * FROM product WHERE user_id = '"+user_id+"'");
            rs = st.executeQuery(query);
            while(rs.next()){
                /*
                private Integer pId;
                private String namaProduk;
                private String description;
                private String price;
                private byte[] photoUrl;
                private Date createdAt;
                private String imageType;
                private String imageName;
                private User userId;*/
                es[i] = new Product();
                es[i].setPId(rs.getInt("p_Id"));
                es[i].setNamaProduk(rs.getString("namaProduk"));
                es[i].setDescription(rs.getString("description"));
                es[i].setPrice(rs.getString("price"));
                es[i].setPhotoUrl(null);
                es[i].setCreatedAt(rs.getTimestamp("created_At"));
                es[i].setImageType(rs.getString("image_Type"));
                es[i].setImageName(rs.getString("image_Name"));
                es[i].setUserId(rs.getInt("user_Id"));
                i++;
            }
        }catch(Exception ex){
            System.out.println("Result: "+ex);
        }
        
        return es;
    }
    
    public boolean deleteProduct(int product_id) {
        String query1;
        query1 = "DELETE FROM product WHERE p_id='"+product_id+"'";
        try {
            st.executeUpdate(query1);
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error2 :" +ex);
            return false;
        }
        
        return true;
    }
    public boolean editProduct(String name, String price, String desc, int pid) {
        String query1;
        query1 = "UPDATE product SET namaProduk = '"+name+"', description = '"+desc+"', price = '"+price+"' WHERE p_id = '"+pid+"' ";
        try {
            st.executeUpdate(query1);
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error2 :" +ex);
            return false;
        }
        
        return true;
    }
}
