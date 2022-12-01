/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Shopping.ProductDAO;
import Shopping.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author liamvu
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

    private static final String ERROR = "adminProductManagement.jsp";
    private static final String SUCCESS = "adminProductManagement.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String category = request.getParameter("category");
            int price = Integer.parseInt(request.getParameter("price"));
            int quantityAdding = Integer.parseInt(request.getParameter("quantityAdding"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String search = request.getParameter("search");
            String productImage = request.getParameter("productImage");
            Date importDate = Date.valueOf(request.getParameter("importDate"));
            int usingDate = Integer.parseInt(request.getParameter("usingDate"));
            quantity += quantityAdding;
            ProductDTO item = new ProductDTO(productID, price, productName, productImage, category, quantity, importDate, usingDate, null);
            ProductDAO dao = new ProductDAO();
            boolean check = dao.updateProduct(item);
            if (check) {
                url = SUCCESS;
                request.setAttribute("CATEGORIES", dao.getCategories());
            } else{
                url = ERROR;
            }
            request.setAttribute("LIST_ITEMS", dao.getListProduct(search));
        } catch (Exception e) {
            log("Error at UpdateProductController: "+e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
