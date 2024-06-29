/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CuongNVT.Controller;

import CuongNVT.Book.BookDTO;
import CuongNVT.Cart.CartObject;
import CuongNVT.CheckOut.OrderDetailDAO;
import CuongNVT.CheckOut.OrderedDAO;
import CuongNVT.Registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "Cart.jsp";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("Cart");
                ArrayList<BookDTO> CartDetails
                = (ArrayList<BookDTO>)session.getAttribute("CartDetails");
                RegistrationDTO account = (RegistrationDTO) session.getAttribute("Account");
                
                String OrderID = null;
                int TotalPayment = OrderedDAO.TotalPayment(cart);
                try { 
                    OrderID = OrderedDAO.addOrder(account.getUsername(), TotalPayment);
                } catch (ClassNotFoundException | SQLException | NamingException ex) {
                }
                try {
                    OrderDetailDAO.addOrderDetails(OrderID, cart);
                } catch (ClassNotFoundException | SQLException | NamingException ex) {
                }
                request.setAttribute("Success_CheckOut", true);
            }
        } finally { 
            RequestDispatcher ReqDis = request.getRequestDispatcher(url);
            ReqDis.forward(request, response);
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
