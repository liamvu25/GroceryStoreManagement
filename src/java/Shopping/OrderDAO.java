/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import Users.UserDTO;
import Utils.DBUtils;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liamvu
 */
public class OrderDAO {

    private static final String CHECKOUT_ORDER1 = "UPDATE tblOrderDetails SET statusID = ? WHERE orderID = ?";
    private static final String CHECKOUT_ORDER2 = "UPDATE tblOrders SET statusID = ?, orderTotal = ? WHERE orderID = ?";
    private static final String UPDATE_ORDER = "UPDATE tblOrderDetails SET quantity=?, price=?, statusID = ? WHERE orderID = ?";
    private static final String REMOVE_ORDER = "DELETE FROM tblOrderDetails WHERE productID = ? AND orderID = ?";
    private static final String GET_PREVIOUS_ORDER = "SELECT orderID FROM tblOrders WHERE userID = ? AND statusID = ?";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO tblOrderDetails(orderID, productID, quantity, price, statusID) VALUES(?,?,?,?,?)";
    private static final String CREATE_ORDER = "INSERT INTO tblOrders(userID, orderDate, orderTotal, statusID) VALUES(?,?,?,?)";
    private static final String GET_ORDER = "SELECT orderID, orderDate, orderTotal FROM tblOrders o, tblStatus s  WHERE o.statusID = s.statusID AND userID = ? AND o.statusID = ? ";
    private static final String CONVERT_ORDER = "SELECT o.productID, p.productName, p.productImage, c.categoryName, o.quantity, o.price, p.importDate, p.usingDate, o.statusID FROM tblOrderDetails o, tblProducts p, tblCategories c WHERE orderID = ? AND o.productID = p.productID AND c.categoryID = p.categoryID";

    public boolean checkOutOrder(List<ProductDTO> items, int orderID) {
        boolean check = false;
        int total = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECKOUT_ORDER1);
                for (ProductDTO item : items) {
                    stm.setString(1, "0");
                    stm.setInt(2, orderID);
                    check = stm.executeUpdate() > 0;
                    total += item.getPrice() * item.getQuantity();
                }
                stm = conn.prepareStatement(CHECKOUT_ORDER2);
                stm.setString(1, "0");
                stm.setInt(2, total);
                stm.setInt(3, orderID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at checkOutOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public boolean updateOrderDetails(List<ProductDTO> items, int orderID, String statusID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_ORDER);
                for (ProductDTO item : items) {
                    stm.setInt(1, item.getQuantity());
                    stm.setInt(2, item.getPrice());
                    stm.setString(3, statusID);
                    stm.setInt(4, orderID);
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
            log("Error at updateOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public boolean removeOrderDetails(List<ProductDTO> items, int orderID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REMOVE_ORDER);
                for (ProductDTO item : items) {
                    stm.setString(1, item.getProductID());
                    stm.setInt(2, orderID);
                    check = stm.executeUpdate() > 0;
                }
            }
        } catch (Exception e) {
            log("Error at updateOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public int getPreviousOrder(UserDTO loginUser) {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PREVIOUS_ORDER);
                stm.setInt(1, loginUser.getUserID());
                stm.setInt(2, 1);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = rs.getInt("orderID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getPreviousOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }

    public boolean createOrderDetails(List<ProductDTO> items, int orderID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_ORDER_DETAIL);
                for (ProductDTO item : items) {
                    stm.setInt(1, orderID);
                    stm.setString(2, item.getProductID());
                    stm.setInt(3, item.getQuantity());
                    stm.setDouble(4, item.getPrice());
                    stm.setInt(5, 1);
                    stm.executeUpdate();
                    check = true;
                }
            }
        } catch (Exception e) {
            log("Error at createOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public int createOrder(int total, List<ProductDTO> items, UserDTO user) {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, user.getUserID());
                stm.setDate(2, new Date(System.currentTimeMillis()));
                stm.setDouble(3, total);
                stm.setInt(4, 1);
                if (stm.executeUpdate() > 0) {
                    rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        int orderID = rs.getInt(1);
                        createOrderDetails(items, orderID);
                        check = orderID;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at createOrder() in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }

    public List<OrderDTO> getOrderByUserID(int userID, String statusID) {
        List<OrderDTO> order = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ORDER);
                stm.setInt(1, userID);
                stm.setString(2, statusID);
                rs = stm.executeQuery();
                order = new ArrayList<>();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    Date orderDate = rs.getDate("orderDate");
                    int total = rs.getInt("orderTotal");
                    order.add(new OrderDTO(orderID, userID, orderDate, total, statusID));
                }
            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return order;
    }

    public List<ProductDTO> convertOrderToItems(int orderID) {
        List<ProductDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CONVERT_ORDER);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    String statusID = rs.getString("statusID");
                    String productName = rs.getString("productName");
                    String productImage = rs.getString("statusID");
                    String category = rs.getString("categoryName");
                    Date importDate = rs.getDate("importDate");
                    int usingDate = rs.getInt("usingDate");
                    list.add(new ProductDTO(productID, price, productName, productImage, category, quantity, importDate, usingDate, statusID));
                }
            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }
}
