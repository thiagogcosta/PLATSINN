/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.google.gson.Gson;
import extratormdadoswatson.InstanciaEstatica.InstanciaEstaticaCD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thiag
 */
@WebServlet(name = "CadastrarInstanciaEstaticaServlet", urlPatterns = {"/CadastrarInstanciaEstaticaServlet"})
public class CadastrarInstanciaEstaticaServlet extends HttpServlet {

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
        
        String nome; 
        String site; 
        int id_entidade;
        int id_tipoEstatico;
        boolean validate = false;
        
        nome =  request.getParameter("nome");
        site =  request.getParameter("site");
        id_entidade =  Integer.parseInt(request.getParameter("id_entidade"));
        id_tipoEstatico =  Integer.parseInt(request.getParameter("id_tipo_estatico"));
        
        InstanciaEstaticaCD inst = new InstanciaEstaticaCD();
        try{
            validate = inst.criarInstanciaEstatica(nome,site,id_entidade,id_tipoEstatico);
            
        }catch(Exception e){
            System.err.println(e);
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
        return "Servlet responsável por cadastrar instância estática!";
    }

}
