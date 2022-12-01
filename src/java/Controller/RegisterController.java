/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import External.DLC;
import External.MessageSpecified;
import Users.UserDAO;
import Users.UserDTO;
import Users.UserError;
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
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    private final String SUCCESS = "welcomePage.jsp";
    private final String ERROR = "register.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            char ch = '*';
            String userName = request.getParameter("userName");
            String userFullName = request.getParameter("userFullName");
            String userAddress = request.getParameter("userAddress");
            String userPhone = request.getParameter("userPhone");
            String userEmail = request.getParameter("userEmail");
            Date userBirthday = Date.valueOf(request.getParameter("userBirthday"));
            String password = request.getParameter("password");
            String confirmedPassword = request.getParameter("confirm");
            UserError userError = DLC.checkValidInfoUser(userName, password, confirmedPassword, userFullName, userAddress, userPhone);
            for(int i = userPhone.length() - 3; i < userPhone.length();i++){
                userPhone = userPhone.substring(0, i) + ch +userPhone.substring(i+1);
            }
            UserDTO user = new UserDTO(0, userName, userFullName, userAddress, userPhone, userBirthday, userEmail, password, null, null);
            if (userError == null) {
                UserDAO dao = new UserDAO();
                boolean checkUpdate = dao.createUser(user);
                if (checkUpdate) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            if (e.getMessage().contains("UNIQUE KEY")) {
                MessageSpecified ms = new MessageSpecified("USERNAME DUPLICATED", "USERNAME DUPLICATED", "This username has been used!");
                request.setAttribute("ERROR_MESSAGE", ms);
            }
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
