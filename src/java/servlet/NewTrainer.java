/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.Trainer;
import beans.TrainerEJB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam
 */
public class NewTrainer extends HttpServlet {

    @EJB TrainerEJB miEjb;
    /**
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
            out.println("<title>Servlet NewTrainer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewTrainer at " + request.getContextPath() + "</h1>");
            // Comprobamos si han pulsado el botón
            if ("Guardar".equals(request.getParameter("alta"))) {
                // Tenemos que crear el camión
                String nombre = request.getParameter("nombre");

                int potions = Integer.parseInt(request.getParameter("potions"));

                int pokeballs = Integer.parseInt(request.getParameter("pokeballs"));

                int points = Integer.parseInt(request.getParameter("points"));

                Trainer trainer = new Trainer(nombre, pokeballs, potions, points);

                if (miEjb.insertarTrainer(trainer)) {
                    out.println("<p>Trainer dado de alta</p>");
                } else {
                    out.println("<p>Ya existe un trainer con ese nombre</p>");
                }
                out.println("<p><a href=\"index.html\">Volver al menú principal</a></p>");
            } else {

                out.println("<form method=\"POST\">");
                out.println("Nombre: <input type=\"text\" name=\"nombre\">");
                out.println("Potions: <input type=\"number\" name=\"potions\">");
                out.println("Pokeballs: <input type=\"number\" name=\"pokeballs\">");
                out.println("Points: <input type=\"number\" name=\"points\">");
                out.println("<input type=\"submit\" name=\"alta\" value=\"Guardar\">");
                out.println("</form>");
            }
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
