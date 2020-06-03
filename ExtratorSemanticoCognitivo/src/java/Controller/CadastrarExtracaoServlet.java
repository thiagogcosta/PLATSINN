/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import extratormdadoswatson.Extrator.Busca;
import extratormdadoswatson.InstanciaDinamica.ExtracaoCD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thiag
 */
@WebServlet(name = "CadastrarExtracaoServlet", urlPatterns = {"/CadastrarExtracaoServlet"})
public class CadastrarExtracaoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String resultado)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print(resultado);
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
        
        boolean validate = false;
        
        String link; 
        int profundidade; 
        String tipo_dinamico;
        
        link =  request.getParameter("link");
        tipo_dinamico =  request.getParameter("tipo_dinamico");
        profundidade =  Integer.parseInt(request.getParameter("profundidade"));
        
        
        try {
            Busca b = new Busca(link,profundidade,tipo_dinamico);
            ExtracaoCD ext = new ExtracaoCD();
            validate  = ext.cadastrarExtracao(b.getR());
            
        } catch (Exception ex) {
            System.out.println("ERRO: "+ex);
            
            Gson gson = new Gson();
            String resposta = gson.toJson(validate);
            processRequest(request, response, resposta);
        }
        
        Gson gson = new Gson();
        String resposta = gson.toJson(validate);
        
        processRequest(request, response, resposta);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet responsável por cadastrar extração!";
    }

}
