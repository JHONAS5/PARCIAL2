
package com.emergentes.DAO;

import com.emergentes.modelo.categorias;
import java.util.List;

public interface categoriasDAO {
    public void insert(categorias categorias) throws Exception;
    public void update(categorias categorias) throws Exception;
    public void delete(int id) throws Exception;
    public categorias getById(int id) throws Exception;
    public List<categorias> getAll() throws Exception;

}
