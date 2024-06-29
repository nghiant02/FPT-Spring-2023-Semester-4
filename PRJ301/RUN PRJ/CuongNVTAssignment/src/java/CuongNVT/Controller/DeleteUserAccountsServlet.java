package CuongNVT.Controller;

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
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeleteUserAccountsServlet", urlPatterns = {"/DeleteUserAccountsServlet"})
public class DeleteUserAccountsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "ManageUserAccounts.jsp";
        try {
            String SearchValue = request.getParameter("SearchValue").trim();
            request.setAttribute("SearchValue", SearchValue);
            String DeleteUsers[] = request.getParameterValues("DeleteValue");
            RegistrationDTO account = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                account = (RegistrationDTO) session.getAttribute("Account");
            }
            if (DeleteUsers != null) {
                for (String DeleteUser : DeleteUsers) {
                    if (!account.getUsername().equals(DeleteUser)) {
                        try {
                            RegistrationDAO.deleteAccount(DeleteUser);
                        } catch (SQLException | NamingException | ClassNotFoundException ex) {
                        }
                    } else { 
                    request.setAttribute("Fault_DeleteCurrentUser", "An unexpected error uccurred. We cannot delete your Account: " + DeleteUser);
                    }
                }
            }

            ArrayList<RegistrationDTO> list = new ArrayList<>();
            if (!SearchValue.equals("")) {
                try {
                    list = RegistrationDAO.getAccounts(SearchValue);
                } catch (SQLException | NamingException | ClassNotFoundException ex) {
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
