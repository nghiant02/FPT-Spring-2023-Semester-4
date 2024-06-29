package CuongNVT.Controller;

import CuongNVT.Registration.RegistrationDAO;
import CuongNVT.Registration.RegistrationDTO;
import CuongNVT.Registration.RegistrationFaultCatch;
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

@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String NewUsername = request.getParameter("txtNewUsername");
            String NewPassword = request.getParameter("txtNewPassword");
            String ConfirmPassword = request.getParameter("txtConfirmPassword");
            String FullName = request.getParameter("txtFullName");
            
            request.setAttribute("NewUsername", NewUsername);
            request.setAttribute("FullName", FullName);
            
            if (NewUsername == null || NewPassword == null
                    || ConfirmPassword == null || FullName == null) {
                url = "SignUp.jsp";
            } else {
                RegistrationFaultCatch RFC = new RegistrationFaultCatch();
                boolean PasswordFaultCheck = RFC.updatePasswordFault(NewPassword, 6, 20);
                boolean ConfirmFaultCheck = RFC.updateConfirmFault(ConfirmPassword, NewPassword);
                boolean FullNameFaultCheck = RFC.updateFullNameFault(FullName, 2, 40);
                boolean UsernameFaultCheck = RFC.updateUsernameFault(NewUsername, 6, 20);

                if (UsernameFaultCheck == false
                        && PasswordFaultCheck == false
                        && ConfirmFaultCheck == false
                        && FullNameFaultCheck == false) {
                    try {
                    RegistrationDAO.insertAccount(NewUsername, NewPassword, false, FullName);
                    } catch (SQLException | NamingException | ClassNotFoundException ex) {
                    }
                    RegistrationDTO NewAccount = null;
                    try {
                        NewAccount = RegistrationDAO.getAccount(NewUsername);
                    } catch (SQLException | NamingException | ClassNotFoundException ex) {
                    }

                    Cookie CookieUsernameObject = new Cookie("Username", NewUsername);
                    Cookie CookiePasswordObject = new Cookie("Password", NewPassword);
                    CookieUsernameObject.setMaxAge(30);
                    CookiePasswordObject.setMaxAge(30);
                    response.addCookie(CookieUsernameObject);
                    response.addCookie(CookiePasswordObject);

                    HttpSession session = request.getSession(true);
                    session.setAttribute("Username", NewUsername);
                    session.setAttribute("Password", NewPassword);
                    session.setAttribute("Account", NewAccount);

                    url = "Welcome.jsp";
                } else {
                    request.setAttribute("RFC", RFC);
                    url = "SignUp.jsp";
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
