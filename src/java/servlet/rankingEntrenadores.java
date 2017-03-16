/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.Pokemon;
import static Entity.Pokemon_.trainer;
import Entity.Trainer;
import beans.TrainerEJB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam
 */
@WebServlet(name = "rankingEntrenadores", urlPatterns = {"/rankingEntrenadores"})
public class rankingEntrenadores extends HttpServlet {

    @EJB
    TrainerEJB miEjb;

    /**
     * /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet rankingEntrenadores</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet rankingEntrenadores at " + request.getContextPath() + "</h1>");

            List<Trainer> train = miEjb.selectTrainerPoints();

            for (Trainer t : train) {
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th bgcolor='#666666'>");
                out.println("TRAINER");
                out.println("</th>");
                out.println("<th>");
                out.println("POINTS");
                out.println("</th>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>");
                out.println(t.getName());
                out.println("</th>");
                out.println("<th>");
                out.println(t.getPoints());
                out.println("</th>");

                out.println("</th>");
                out.println("</tr>");

                out.println("</table>");

            }

            out.println("</body>");
            out.println("</html>");
            out.println("</body>");
            out.println("</html>");
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
