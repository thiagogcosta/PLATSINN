/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import extratormdadoswatson.WATSON.LanguageTranslateIBMWatson;
import extratormdadoswatson.WATSON.NLUnderstandIBMWatson;
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
@WebServlet(name = "ObterAnaliseCognitiva", urlPatterns = {"/ObterAnaliseCognitiva"})
public class ObterAnaliseCognitiva extends HttpServlet {

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
        
        String texto; 
        int limite; 
        String texto_analisado = "";
        
        texto =  request.getParameter("texto");
        
        System.out.println("TEXTO: "+texto);
        
        LanguageTranslateIBMWatson lang = new LanguageTranslateIBMWatson();
        String traducao = lang.traducao(texto);
        
        System.out.println("TEXTO TRADUZIDO: "+traducao);
        
        NLUnderstandIBMWatson nlu = new NLUnderstandIBMWatson(traducao);
        try {
            texto_analisado = nlu.getAnaliseCognitiva();
            //texto_analisado = texto_analisado.replaceAll(" ",""); 
        } catch (Exception ex) {
           System.out.println(ex);
        }
        
        System.out.println("ANÁLISE: "+texto_analisado);
        
        processRequest(request, response, texto_analisado);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Obtém uma análise cognitiva de um conteúdo utilizando o IBM Watson!";
    }// </editor-fold>

}
