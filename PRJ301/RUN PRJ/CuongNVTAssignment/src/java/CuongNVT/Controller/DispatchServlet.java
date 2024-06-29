
package CuongNVT.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class DispatchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String btAction = request.getParameter("btAction");
            if (btAction == null || btAction.equals("Submit") || btAction.equals("Back to Log In") || btAction.equals("Back")) {
                url = "LogInServlet"; 
            } else if (btAction.equals("Sign Up")){
                url = "SignUpServlet";
            } else if (btAction.equals("Sign Out")){
               url = "SignOutServlet";
            } else if (btAction.equals("Manage User Accounts")){
                url = "ManageUserAccountsServlet";
            } else if (btAction.equals("Go to the Book Store")){
                url = "BookStoreServlet";
            } else if (btAction.equals("View your Cart")){
                url = "CartServlet";
            } else if (btAction.equals("Check Out")){
                url = "CheckOutServlet";
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
