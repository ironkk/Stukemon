/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.TrainerEJB;
import Entity.Pokemon;
import Entity.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dam
 */


public class mejorarVida extends HttpServlet {

    @EJB
    TrainerEJB miEjb;

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
            out.println("<title>Servlet mejorarVida</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> mejorar VIDA </h1>");

            if ("mejorarVida".equals(request.getParameter("mejorarVida"))) {

                String s = request.getParameter("entrenador");

                List<Pokemon> pikachu = miEjb.selectPokemonTrainer(s);

                if (pikachu.size() > 0) {

                    out.print("<form action='mejorarVida' method='POST'>");
                    out.print("Escoge un pokemon: <select name='pokemon'>");

                    for (Pokemon p : pikachu) {

                        out.print("<option value ='" + p.getName() + "'>" + p.getName() + "</option>");

                    }

                    out.print("</select>");

                    out.print(" <input type='hidden' value ='" + s + "' name='entrenador'><input type='submit' value='dale' name='dale'>");
                    out.print("</form>");

                } else {

                    out.print("No tienes pokemons");

                }

            } else if ("dale".equals(request.getParameter("dale"))) {

                String pokemon = request.getParameter("pokemon");
                String entrenador = request.getParameter("entrenador");

                boolean correcto = miEjb.modificarModificar(s);
                boolean correcto2 = miEjb.modificarPokemonVida(pokemon);

                if (correcto) {

                    out.print("");

                } else {
                    out.print("no va");
                }
                if (correcto2) {

                    out.print("<br> El pokemon " + pokemon + " tienes +50 ");
                } else {
                    out.print("error");

                }

            } else {
            //mostrar una lista de trainers que almenos tengan un pokemon
                List<Trainer> pikachu2 = miEjb.();

                out.print("<form action='MejorarVida' method='POST'>");
                out.print("Selecciona el entrenador <select name='entrenador'>");
                for (Trainer t : pikachu2) {

                    out.print("<option value ='" + t.getName() + "'>" + t.getName() + "</option>");

                }
                out.print("</select>");
                out.print(" <input type='submit' name = 'mejorarVida' value='mejorarVida'>");

                out.print("</form>");
                // Fin Primer formulario
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
