/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author liamvu
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private final String LOGIN = "Login";
    private final String LOGIN_CONTROLLER = "LoginController";
    private final String LOGOUT = "Logout";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String ERROR = "error.jsp";
    private final String REGISTER = "Register";
    private final String REGISTER_CONTROLLER = "RegisterController";
    private final String SEARCH_USER = "Search";
    private final String SEARCH_USER_CONTROLLER = "SearchUserController";
    private final String UPDATE_USER ="Confirm Update";
    private final String UPDATE_USER_CONTROLLER="UpdateUserController";
    //private final String ADMIN_UPDATE = "Update";
    //private final String ADMIN_UPDATE_CONTROLLER="update.jsp";
    private final String CHANGE_STATUS ="ChangeStatus";
    private final String CHANGE_STATUS_CONTROLLER="ChangeStatusController";
    private final String CREATE_PRODUCT = "CreateProduct";
    private final String CREATE_PRODUCT_CONTROLLER = "CreateProductController";
    private final String CHANGE_STATUS_PRODUCT = "ChangeStatusProduct";
    private final String CHANGE_STATUS_PRODUCT_CONTROLLER="ChangeStatusProductController";
    private final String SEARCH_PRODUCT = "SearchProduct";
    private final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private final String UPDATE_PRODUCT = "ModifyItem";
    private final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    private final String USER_VIEW_HISTORY = "ViewHistory";
    private final String USER_VIEW_HISTORY_CONTROLLER = "LoadOrderController";
    private final String ADMIN_VIEW_HISTORY ="ViewHistoryAdmin";
    private final String ADMIN_VIEW_HISTORY_CONTROLLER="LoadOrderAdminController";
    private final String VIEW_ORDER_DETAIL = "MoreOrderDetails";
    private final String VIEW_ORDER_DETAIL_CONTROLLER="LoadOrderDetailController";
    private final String SHOPPING_LOAD_ITEMS = "LoadItem";
    private final String SHOPPING_LOAD_ITEMS_CONTROLLER="LoadItemController";
    private final String SHOPPING_ADD_TO_CART = "AddToCart";
    private final String SHOPPING_ADD_TO_CART_CONTROLLER = "AddToCartController";
    private final String SHOPPING_UPDATE_CART = "UpdateCart";
    private final String SHOPPING_UPDATE_CART_CONTROLLER="UpdateCartController";
    private final String REMOVE_ITEM_IN_CART ="RemoveItemInCart";
    private final String CLEAR_CART ="ClearCart";
    private final String REMOVE_ITEM_IN_CART_CONTROLLER = "RemoveItemCartController";
    private final String CHECKOUT = "Checkout";
    private final String CHECKOUT_CONTROLLER = "CheckoutController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            }else if(LOGOUT.equals(action)){
                url = LOGOUT_CONTROLLER;
            }else if(REGISTER.equals(action)){
                url = REGISTER_CONTROLLER;
            }else if(SEARCH_USER.equals(action)){
                url = SEARCH_USER_CONTROLLER;
            }else if(UPDATE_USER.equals(action)){
                url = UPDATE_USER_CONTROLLER;
            }else if(CHANGE_STATUS.equals(action)){
                url=CHANGE_STATUS_CONTROLLER;
            }else if(CREATE_PRODUCT.equals(action)){
                url=CREATE_PRODUCT_CONTROLLER;
            }else if(CHANGE_STATUS_PRODUCT.equals(action)){
                url = CHANGE_STATUS_PRODUCT_CONTROLLER;
            }else if(SEARCH_PRODUCT.equals(action)){
                url = SEARCH_PRODUCT_CONTROLLER;
            }else if(UPDATE_PRODUCT.equals(action)){
                url = UPDATE_PRODUCT_CONTROLLER;
            }else if(USER_VIEW_HISTORY.equals(action)){
                url = USER_VIEW_HISTORY_CONTROLLER;
            }else if(ADMIN_VIEW_HISTORY.equals(action)){
                url = ADMIN_VIEW_HISTORY_CONTROLLER;
            }else if(VIEW_ORDER_DETAIL.equals(action)){
                url = VIEW_ORDER_DETAIL_CONTROLLER;
            }else if (SHOPPING_ADD_TO_CART.equals(action)){
                url = SHOPPING_ADD_TO_CART_CONTROLLER;
            }else if(REMOVE_ITEM_IN_CART.equals(action)){
                url = REMOVE_ITEM_IN_CART_CONTROLLER;
            }else if(CLEAR_CART.equals(action)){
                url = REMOVE_ITEM_IN_CART_CONTROLLER;
            }else if(CHECKOUT.equals(action)){
                url = CHECKOUT_CONTROLLER;
            }else if(SHOPPING_LOAD_ITEMS.equals(action)){
                url = SHOPPING_LOAD_ITEMS_CONTROLLER;
            }else if(SHOPPING_UPDATE_CART.equals(action)){
                url = SHOPPING_UPDATE_CART_CONTROLLER;
            }else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "Action is not available");
            }
        } catch (Exception e) {
        } finally {
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
