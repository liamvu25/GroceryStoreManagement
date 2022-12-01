/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author liamvu
 */
public class ProductDTO implements Serializable{
    private String productID;
    private int price;
    private String productName;
    private String image;
    private String category;
    private int quantity;
    private Date importDate;
    private int usingDate;
    private String status;

    public ProductDTO() {
    }

    public ProductDTO(String productID, int price, String productName, String image, String category, int quantity, Date importDate, int usingDate, String status) {
        this.productID = productID;
        this.price = price;
        this.productName = productName;
        this.image = image;
        this.category = category;
        this.quantity = quantity;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(int usingDate) {
        this.usingDate = usingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
