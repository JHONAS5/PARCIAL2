
package com.emergentes.controlador;

import com.emergentes.DAO.librosDAO;
import com.emergentes.DAO.librosDAOimpl;
import com.emergentes.modelo.libros;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "InicioL", urlPatterns = {"/InicioL"})
public class InicioL extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
        //AvisoController? action=add
        librosDAO dao = new librosDAOimpl();
        int id;
        libros avi = new libros();
        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        
        switch(action){
            case "add":
                request.setAttribute("libros", avi);
                request.getRequestDispatcher("frmlibros.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                avi = dao.getById(id);
                System.out.println(avi);
                request.setAttribute("libros",avi);
                request.getRequestDispatcher("frmlibros.jsp").forward(request, response);
                break;

            case"delete":
                id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                response.sendRedirect(request.getContextPath() + "/inicioL");
                break;

            case "view":
                List<libros> lista = dao.getAll();
                request.setAttribute("libros", lista);
                request.getRequestDispatcher("indexL.jsp").forward(request, response);
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
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String disponible = request.getParameter("disponible");
        String categoria = request.getParameter("categoria");
        
        libros avi  = new libros();
         
        avi.setId(id);
        avi.setTitulo(titulo);
        avi.setAutor(autor);
        avi.setDisponible(disponible);
        avi.setCategoria(categoria);
              
        if (id == 0) {
            try {
                librosDAO dao = new librosDAOimpl();
                dao.insert(avi);
                response.sendRedirect(request.getContextPath()+"/inicioL");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                librosDAO dao = new librosDAOimpl();
                dao.update(avi);
                response.sendRedirect(request.getContextPath() + "/inicioL");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        
    }

}
