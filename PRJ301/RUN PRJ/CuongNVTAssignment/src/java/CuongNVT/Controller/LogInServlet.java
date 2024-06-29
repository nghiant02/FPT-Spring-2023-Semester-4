/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CuongNVT.Controller;

import CuongNVT.Registration.RegistrationDAO;
import CuongNVT.Registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})
public class LogInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String Username = request.getParameter("txtUsername");
            String Password = request.getParameter("txtPassword");

            String CookieUsername = null, CookiePassword = null;
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("Username")) {
                        CookieUsername = ck.getValue();
                        break;
                    }

                }
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("Password")) {
                        CookiePassword = ck.getValue();
                        break;
                    }
                }

            }

            String SessionUsername = null, SessionPassword = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                try {
                    SessionUsername = session.getAttribute("Username").toString();
                } catch (NullPointerException NPE) {
                }
                try {
                    SessionPassword = session.getAttribute("Password").toString();
                } catch (NullPointerException NPE) {
                }
            }
            
            if ((Username == null || Password == null)
                    && (CookieUsername == null || CookiePassword == null)
                    && (SessionUsername == null || SessionPassword == null)){
                url = "LogIn.html";
            } else {
            
            RegistrationDTO Account = null,
                    CookieAccount = null, SessionAccount = null;

            String priority = "";
            try {
                CookieAccount = RegistrationDAO.getAccount(CookieUsername, CookiePassword);
                if (CookieAccount != null) {
                    priority = "Cookie";
                }
            } catch (SQLException | NamingException | ClassNotFoundException ex) {
            }

            try {
                SessionAccount = RegistrationDAO.getAccount(SessionUsername, SessionPassword);
                if (SessionAccount != null) {
                    priority = "Session";
                }
            } catch (SQLException | NamingException | ClassNotFoundException ex) {

                }

            try {
                Account = RegistrationDAO.getAccount(Username, Password);
                if (Account != null) {
                    priority = "Request";
                }
            } catch (SQLException | NamingException | ClassNotFoundException ex) {
            }

            if (priority.equals("")) {
                url = "Fault.html";
            }

            if (priority.equals("Cookie")) {
                HttpSession sessionCookie = request.getSession(true);
                sessionCookie.setAttribute("Username", CookieUsername);
                sessionCookie.setAttribute("Password", CookiePassword);
                sessionCookie.setAttribute("Account", CookieAccount);
                url = "Welcome.jsp";
            }

            if (priority.equals("Session")) {
                url = "Welcome.jsp";
            }

            if (priority.equals("Request")) {
                Cookie CookieUsernameObject = new Cookie("Username", Username);
                Cookie CookiePasswordObject = new Cookie("Password", Password);
                CookieUsernameObject.setMaxAge(30);
                CookiePasswordObject.setMaxAge(30);
                response.addCookie(CookieUsernameObject);
                response.addCookie(CookiePasswordObject);

                HttpSession sessionRequest = request.getSession(true);
                sessionRequest.setAttribute("Username", Username);
                sessionRequest.setAttribute("Password", Password);
                sessionRequest.setAttribute("Account", Account);
                url = "Welcome.jsp";
            }
            }
        } finally {
            RequestDispatcher ReqDis = request.getRequestDispatcher(url);
            ReqDis.forward(request, response);
        }
    }

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
