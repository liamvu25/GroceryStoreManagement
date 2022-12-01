/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shopping;

import Utils.DBUtils;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liamvu
 */
public class ProductDAO {

    private static final String GET_CATEGORYID = "SELECT categoryID FROM tblCategories WHERE categoryName = ?";
    private static final String GET_CATEGORY = "SELECT categoryName FROM tblCategories";
    private static final String CHANGE_STATUS = "UPDATE tblProducts SET statusID=? WHERE productID = ?";
    private static final String UPDATE_PRODUCT = "UPDATE tblProducts SET productName=?, productPrice = ?, productImage = ?, categoryID = ?, quantity = ?, importDate = ?, usingDate = ? WHERE productID = ?";
    private static final String GET_LIST_PRODUCT = "SELECT productID, productPrice, productName, productImage,categoryName, quantity, importDate, usingDate, statusID FROM tblProducts p, tblCategories c WHERE p.categoryID = c.categoryID AND productName like ?";
    private static final String CHECK_QUANTITY = "SELECT quantity, statusID FROM tblProducts WHERE productID like ?";
    private static final String PRODUCT_SOLD = "SELECT quantity FROM tblProducts WHERE productID = ?";
    private static final String UPDATE_QUANTITY = "UPDATE tblProducts SET quantity = ? WHERE productID = ?";
    private static final String CREAT_PRODUCT="INSERT INTO tblProducts(productID, productPrice, productName, productImage, categoryID, quantity, importDate, usingDate, statusID) VALUES(?,?,?,?,?,?,?,?,?) ";
    private String getCategoryID(String category) {
        String result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CATEGORYID);
                stm.setString(1, category);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("categoryID");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getCategoryID in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return result;
    }

    public List<String> getCategories() {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_CATEGORY);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("categoryName"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getCategories in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }

    public boolean changeProductStatus(ProductDTO item) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHANGE_STATUS);
                stm.setString(1, item.getStatus().equals("1") ? "0" : "1");
                stm.setString(2, item.getProductID());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at changeProductStatus in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public boolean updateProduct(ProductDTO item) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_PRODUCT);
                stm.setString(1, item.getProductName());
                stm.setInt(2, item.getPrice());
                stm.setString(3, item.getImage());
                stm.setString(4, getCategoryID(item.getCategory()));
                stm.setInt(5, item.getQuantity());
                stm.setString(6, item.getImportDate().toString());
                stm.setInt(7, item.getUsingDate());
                stm.setString(8, item.getProductID());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at updateProduct in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_LIST_PRODUCT);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    int price = rs.getInt("productPrice");
                    String productName = rs.getString("productName");
                    String productImage = rs.getString("productImage");
                    String categoryName = rs.getString("categoryName");
                    int quantity = rs.getInt("quantity");
                    Date importDate = rs.getDate("importDate");
                    int usingDate = rs.getInt("usingDate");
                    String statusID = rs.getString("statusID");
                    if (quantity == 0) {
                        statusID = "0";
                    }
                    ProductDTO product = new ProductDTO(productID, price, productName, productImage, categoryName, quantity, importDate, usingDate, statusID);
                    list.add(product);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at getListProduct in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }

    public int checkQuantity(ProductDTO item) {
        int check = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_QUANTITY);
                stm.setString(1, item.getProductID());
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quantityStorage = rs.getInt("quantity");
                    String statusID = rs.getString("statusID");
                    boolean cp = item.getQuantity() <= quantityStorage;
                    if (statusID.equals("1") && cp) {
                        check = 0;
                    } else {
                        check = quantityStorage;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at checkQuantity in ProductDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }
    public int productSold(List<ProductDTO> items){
        int check = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                for (ProductDTO item : items){
                    stm = conn.prepareStatement(PRODUCT_SOLD);
                    stm.setString(1, item.getProductID());
                    rs = stm.executeQuery();
                    int quantityStorage = 0;
                    int quantityRemain = 0;
                    if (rs.next()){
                        quantityStorage = rs.getInt("quantity");
                        quantityRemain = quantityStorage - item.getQuantity();
                    }
                    if (quantityRemain >= 0){
                        stm = conn.prepareStatement(UPDATE_QUANTITY);
                        stm.setInt(1, quantityRemain);
                        stm.setString(2, item.getProductID());
                        if (stm.executeUpdate() > 0) {
                            check++;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at productSold in ProductDAO: " + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }
    public boolean createProduct(ProductDTO item){
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                stm = conn.prepareStatement(CREAT_PRODUCT);
                stm.setString(1, item.getProductID());
                stm.setInt(2, item.getPrice());
                stm.setString(3, item.getProductName());
                stm.setString(4, item.getImage());
                stm.setString(5, getCategoryID(item.getCategory()));
                stm.setInt(6, item.getQuantity());
                stm.setDate(7, item.getImportDate());
                stm.setInt(8, item.getUsingDate());
                stm.setString(9, item.getStatus());
                check = stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at updateProduct in ProductDAO: " + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
}
