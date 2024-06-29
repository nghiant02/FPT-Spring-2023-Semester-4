/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CuongNVT.Controller;

import CuongNVT.Book.BookDAO;
import CuongNVT.Book.BookDTO;
import CuongNVT.Cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

@WebServlet(name = "PutItemsToCartServlet", urlPatterns = {"/PutItemsToCartServlet"})
public class PutItemsToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "BookStore.jsp";
        try {
            boolean Fault_WrongNumberFormat = false;
            String SearchValue = request.getParameter("SearchValue").trim();
            request.setAttribute("SearchValue", SearchValue);

            ArrayList<BookDTO> books = new ArrayList<>();
            if (!SearchValue.equals("")) {
                try {
                    books = BookDAO.getBooks(SearchValue);
                } catch (SQLException | NamingException | ClassNotFoundException ex) {
                }
            }
            request.setAttribute("Books", books);

            HttpSession session = request.getSession(false);
            CartObject cart = null;
            if (session != null) {
                cart = (CartObject) session.getAttribute("Cart");
                if (cart == null) {
                    cart = new CartObject();
                }

                for (BookDTO book : books) {
                    String currentPara = request.getParameter(book.getID() + "_Quantity");
                    if (!currentPara.equals("")) {
                        int currentQuantity = 0;
                        try {
                            currentQuantity = Integer.parseInt(currentPara);
                            if (currentQuantity < 1) {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException ex) {
                            Fault_WrongNumberFormat = true;
                            request.setAttribute("Fault_WrongNumberFormat", Fault_WrongNumberFormat);
                        }
                        if (currentQuantity != -1) {
                            cart.addBookToCart(book.getID(), currentQuantity);

                        }
                    }
                }

            }
            
            if (!Fault_WrongNumberFormat){
            session.setAttribute("Cart", cart);
            ArrayList<BookDTO> CartDetails = new ArrayList<>();
            for (String current: cart.getItems().keySet()){
                BookDTO book = null;
                try {
                    book = BookDAO.getBook(current);
                } catch (SQLException | NamingException | ClassNotFoundException ex) {  }
                   CartDetails.add(book);
            }
            session.setAttribute("CartDetails", CartDetails);
            request.setAttribute("Success_Purchase", true);
            }
            
            Map<BookDTO, String> QuantityValues = new HashMap<>();
            for (BookDTO book : books) {
                QuantityValues.put(book, request.getParameter(book.getID() + "_Quantity"));
            }
            request.setAttribute("QuantityValues", QuantityValues);

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
