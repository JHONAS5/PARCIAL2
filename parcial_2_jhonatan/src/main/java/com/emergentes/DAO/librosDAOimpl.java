
package com.emergentes.DAO;

import com.emergentes.modelo.libros;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class librosDAOimpl extends ConexionDB implements librosDAO{
    
    @Override
    public void insert(libros libros) throws Exception {
        try{
       this.conectar();
       PreparedStatement ps = this.conn.prepareStatement("insert into libros (titulo, autor, disponible, categoria) values (?, ?, ?, ?)");
        ps.setString(1, libros.getTitulo());
        ps.setString(2, libros.getAutor());
        ps.setString(3, libros.getDisponible());
        ps.setString(4, libros.getCategoria());
        ps.executeUpdate();
       }catch (Exception e){
           throw e;
       }finally{
           this.desconectar();
       }
    }

    @Override
    public void update(libros libros) throws Exception {
       try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update libros set titulo =?, autor=?, disponible=?, categoria=? where id = ?");
            ps.setString(1, libros.getTitulo());
            ps.setString(2, libros.getAutor());
            ps.setString(3, libros.getDisponible());
            ps.setString(4, libros.getCategoria());
            ps.setInt(5, libros.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try{
        this.conectar();
        PreparedStatement ps = this.conn.prepareStatement("delete from libros where id = ? ");
        ps.setInt(1, id);
        ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public libros getById(int id) throws Exception {
        
        libros avi = new libros();
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from libros where id=? limit 1");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                avi.setId(rs.getInt("id"));
                avi.setTitulo(rs.getString("titulo"));
                avi.setAutor(rs.getString("autor"));
                avi.setDisponible(rs.getString("disponible"));
                avi.setCategoria(rs.getString("categoria"));
            }
        }catch (Exception e){
              throw e; 
        }finally{
             this.desconectar();
        }
        return avi;
    }

    @Override
    public List<libros> getAll() throws Exception {
        List<libros> lista = null;
        try{
            this.conectar();
            PreparedStatement ps=this.conn.prepareStatement("select * from libros");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<libros>();
            while(rs.next()){
                libros avi = new libros();
                avi.setId(rs.getInt("id"));
                avi.setTitulo(rs.getString("titulo"));
                avi.setAutor(rs.getString("autor"));
                avi.setDisponible(rs.getString("disponible"));
                avi.setCategoria(rs.getString("categoria"));
                lista.add(avi);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
        return lista;
      
    }
}
