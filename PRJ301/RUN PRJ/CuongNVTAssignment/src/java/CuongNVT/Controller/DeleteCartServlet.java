package CuongNVT.Controller;

import CuongNVT.Book.BookDAO;
import CuongNVT.Book.BookDTO;
import CuongNVT.Cart.CartObject;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteCartServlet", urlPatterns = {"/DeleteCartServlet"})
public class DeleteCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "Cart.jsp";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("Cart");
                if (cart != null) {
                    String[] DeleteValues = request.getParameterValues("DeleteValue");
                    if (DeleteValues != null){
                    for (String DeleteValue : DeleteValues) {
                        cart.removeBookFromCart(DeleteValue);
                        }
                    }
                }
                
                ArrayList<BookDTO> CartDetails = new ArrayList<>();
                for (String current: cart.getItems().keySet()){
                BookDTO book = null;
                try {
                    book = BookDAO.getBook(current);
                } catch (SQLException | NamingException | ClassNotFoundException ex) {  }
                CartDetails.add(book);
            }
            session.setAttribute("CartDetails", CartDetails);
       
            session.setAttribute("Cart", cart);
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
