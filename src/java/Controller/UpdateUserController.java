/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.DLC;
import Users.UserDAO;
import Users.UserDTO;
import Users.UserError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
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
@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

    private static final String ERROR = "updateUser.jsp";
    private static final String SUCCESS = "SearchUserController";
    private static final String UPDATE_ADMIN = "LogoutController";
    private static final String UPDATE_USER = "updateSelf.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String userName = request.getParameter("userName");
            String userFullName = request.getParameter("userFullName");
            String userAddress = request.getParameter("userAddress");
            String userPhone = request.getParameter("userPhone");
            Date userBirthday = Date.valueOf(request.getParameter("userBirthday"));
            String userEmail = request.getParameter("userEmail");
            String role = request.getParameter("newRole");
            if (role==null) {
                role = request.getParameter("oldRole");
            }
            String password= request.getParameter("password");
            String confirmedPassword= request.getParameter("confirmedPassword");
            UserError userError = DLC.checkValidInfoUser(userName, password, confirmedPassword, userFullName, userAddress, userPhone);
            UserDTO user = new UserDTO(userID, userName, userFullName, userAddress, userPhone, userBirthday, userEmail, password, role, null); 
             if(userError == null){
                 UserDAO dao = new UserDAO(); 
                List<UserDTO> list = dao.getListUser("");
                request.setAttribute("LIST_USER", list);
                boolean checkUpdate = dao.updateUser(user);
                 if(checkUpdate){
                    url = SUCCESS;
                }
             }else{
                request.setAttribute("USER_UPDATING", user);
                request.setAttribute("USER_ERROR", userError);
             }
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            if(userID == loginUser.getUserID()){
                url = UPDATE_ADMIN;
            }
            if("us".equals(loginUser.getRole())){
                url = UPDATE_USER;
            }
        } catch (Exception e) {
            log("Error at UpdateController"+e.toString());
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
