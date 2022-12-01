/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import java.sql.Date;

/**
 *
 * @author liamvu
 */
public class OrderDTO implements Comparable<OrderDTO>{
    private int orderID;
    private int userID;
    private Date orderDate;
    private int orderTotal;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int userID, Date orderDate, int orderTotal, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public int compareTo(OrderDTO o) {
        if(this.userID > o.userID)
            return 1;
        else if(this.userID < o.userID)
            return -1;
        else return 0;
    }
}
