/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.DAO.categoriasDAO;
import com.emergentes.DAO.categoriasDAOimpl;
import com.emergentes.modelo.categorias;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "InicioC", urlPatterns = {"/InicioC"})
public class InicioC extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
        //AvisoController? action=add
        categoriasDAO dao = new categoriasDAOimpl();
        int id;
        categorias avi = new categorias();
        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                request.setAttribute("categorias", avi);
                request.getRequestDispatcher("frmcategorias.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                    avi = dao.getById(id);
                    System.out.println(avi);
                request.setAttribute("categorias",avi);
                request.getRequestDispatcher("frmcategorias.jsp").forward(request, response);
                break;

            case"delete":
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect(request.getContextPath() + "/inicioC");
                break;

            case "view":
                List<categorias> lista = dao.getAll();
                request.setAttribute("categorias", lista);
                request.getRequestDispatcher("indexC.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }catch(Exception ex){
        System.out.println("Error: "+ ex.getMessage());
    }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id =Integer.parseInt(request.getParameter("id"));
        String categoria = request.getParameter("categoria");
        
        categorias avi  = new categorias();
         
        avi.setId(id);
        avi.setCategoria(categoria);
         
               
        if (id == 0) {
            try {
                categoriasDAO dao = new categoriasDAOimpl();
                dao.insert(avi);
                response.sendRedirect(request.getContextPath()+"/inicioC");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                categoriasDAO dao = new categoriasDAOimpl();
                dao.update(avi);
                response.sendRedirect(request.getContextPath() + "/inicioC");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        
    }


}
