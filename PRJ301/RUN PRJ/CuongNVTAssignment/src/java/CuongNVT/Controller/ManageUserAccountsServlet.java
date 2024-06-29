package CuongNVT.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import CuongNVT.Registration.RegistrationDAO;
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

@WebServlet(urlPatterns = {"/ManageUserAccountsServlet"})
public class ManageUserAccountsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "ManageUserAccounts.jsp";
        try {
            String btAction_ManageUserAccounts
                    = request.getParameter("btAction_ManageUserAccounts");
            if (btAction_ManageUserAccounts == null){
            request.setAttribute("FirstTime", true);
            }
            else if (btAction_ManageUserAccounts.equals("Submit")) {
                
                String SearchValue = request.getParameter("txtSearch").trim();
                request.setAttribute("SearchValue", SearchValue);
                
                ArrayList<RegistrationDTO> list = new ArrayList<>();
                if (!SearchValue.equals("")) {                 
                    try {
                    list = RegistrationDAO.getAccounts(SearchValue);
                    } catch (SQLException | NamingException | ClassNotFoundException ex) {
                    }
                }
                
                request.setAttribute("UserAccounts", list);
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
