/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CuongNVT.Controller;

import CuongNVT.Book.BookDAO;
import CuongNVT.Book.BookDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tucuo
 */
@WebServlet(name = "BookStoreServlet", urlPatterns = {"/BookStoreServlet"})
public class BookStoreServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      String url = "BookStore.jsp";
      try{
           String btAction_BookStore
                    = request.getParameter("btAction_BookStore");
            if (btAction_BookStore == null){
            request.setAttribute("FirstTime", true);
            }
            
            String SearchValue = request.getParameter("txtSearch").trim();
            request.setAttribute("SearchValue", SearchValue);
            ArrayList<BookDTO> books = new ArrayList<>();
            if (!SearchValue.equals("")){
                try {
                    books = BookDAO.getBooks(SearchValue);
                } catch (SQLException | NamingException | ClassNotFoundException ex) {  }
            }
            
            request.setAttribute("Books", books);
            
      } finally{
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
