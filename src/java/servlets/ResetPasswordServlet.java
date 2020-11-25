/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author 839645
 */
public class ResetPasswordServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/reset.jsp";
        String uuid = request.getParameter("uuid");
        HttpSession sess = request.getSession();

        if(uuid != null || uuid.trim().length() == 0){
            if(new AccountService().isUuidValid((String) sess.getAttribute("resetEmail"), uuid)){
                url = "/WEB-INF/resetNewPassword.jsp";
            }
        }
        this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String action = request.getParameter("action");
            String url = "/WEB-INF/reset.jsp";
            HttpSession sess = request.getSession();
            AccountService ac = new AccountService();
            if(action.equals("reset")){
                String email = request.getParameter("email");
                String resetStatus = ac.resetPassword(email, this.getServletContext().getRealPath("/WEB-INF"), request.getRequestURL().toString());

                if(resetStatus.toLowerCase().startsWith("error")){
                    request.setAttribute("errMsg", resetStatus);
                } else{
                    request.setAttribute("infoMsg", resetStatus);
                    sess.setAttribute("resetEmail", email);
                }
            } else if(action.equals("resetPassword")){
                String password = request.getParameter("password");
                String resetPassStatus = ac.setPassword((String) sess.getAttribute("resetEmail"), password);
                
                if(resetPassStatus.toLowerCase().startsWith("error")){
                    request.setAttribute("errMsg", resetPassStatus);
                } else{
                    request.setAttribute("infoMsg", resetPassStatus);
                    new UserDB().deleteUUID((String) sess.getAttribute("resetEmail"));
                }
                
                url = "/WEB-INF/resetNewPassword.jsp";
            }
            
            this.getServletContext().getRequestDispatcher(url).forward(request, response);

    }

    
   

}
