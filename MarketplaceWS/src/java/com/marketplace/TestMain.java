/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marketplace;

import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.exit;
import java.sql.SQLException;
import static javafx.application.Platform.exit;

/**
 *
 * @author Silva
 */
public class TestMain {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        DbConnector con = new DbConnector();
        int a = con.getTotalLike(13);
        Product[] es = con.searchProduct("d", 1);
        int i;
        Purchase[] ex = con.getProductPurchase(7, 1);
        Purchase[] eb = con.getProductPurchase(7,2);
        if(es == null){
            System.out.println("not found");
        }else{
        System.out.println("Purchase = "+es.length);
        for(i=0;i<es.length;i++){
            System.out.println(i+" = "+es[i].getNamaProduk()+ " "+es[i].getCreatedAt());
        }
        System.out.println("Sales = "+eb.length);
        for(i=0;i<eb.length;i++){
            System.out.println(eb[i].getProductName()+" "+eb[i].getCreatedAt().toString());
        }
        
        Purchase test = con.getPhotoPurchase(5);
        Product test2 = con.getPhotoProduct(17);
        
        InputStream ax;
        
        System.out.println(test.getProductPhotourl().toString());
        System.out.println(test2.getPhotoUrl().toString());
        
        System.out.println(a);
//         System.out.println(con.confirmPurchase(7, 17, "consignee", "fulladdress", 2, "creditcardnumber", "postalcode", "phonenumber", "card_verification"));
    }
    }
    
}
