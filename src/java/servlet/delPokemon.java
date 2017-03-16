/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Entity.Pokemon;
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
@WebServlet(name = "delPokemon", urlPatterns = {"/delPokemon"})
public class delPokemon extends HttpServlet {
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
            out.println("<title>Servlet borarPokemon</title>");            
            out.println("</head>");
            out.println("<body>");
            
            if ("Borrar".equals(request.getParameter("borrar"))) {
                
                String name = request.getParameter("pokemon");

                if (miEjb.borrarPokemon(name)) {
                    out.println("<p>Pokemon borrado</p>");
                } else {
                    out.println("<p>Pokemon no borrado</p>");
                }
                out.println("<p><a href=\"index.html\">Volver al menú principal</a></p>");
            } else { 
                
                out.println("<form method=\"POST\">");

                out.println("Pokemon: <select name=\"pokemon\">");
                
                List<Pokemon> pokemons = miEjb.selectAllPokemons();
                for (Pokemon p : pokemons) {
                    out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                }
                out.println("</select>");
                
                out.println("<input type=\"submit\" name=\"borrar\" value=\"Borrar\">");
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
