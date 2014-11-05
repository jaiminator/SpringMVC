/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cursohibernate.spring.dao;

import es.cursohibernate.spring.negocio.EntidadBancaria;
import java.util.List;

/**
 *
 * @author alumno
 */
public interface EntidadBancariaDAO {
    
    void delete(int idEntidadBancaria);
    void insert(EntidadBancaria entidadBancaria);
    void update(EntidadBancaria entidadBancaria);
    List<EntidadBancaria> findAll();
    EntidadBancaria get(int idEntidadBancaria);
    
}
