/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cursohibernate.spring.controller;

import es.cursohibernate.spring.dao.EntidadBancariaDAO;
import es.cursohibernate.spring.json.JsonTransformer;
import es.cursohibernate.spring.json.JsonTransformerImplJackson;
import es.cursohibernate.spring.negocio.EntidadBancaria;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UsuarioController {
    
    @Autowired
    private JsonTransformer jsonTransformer;
    
    @Autowired
    private EntidadBancariaDAO entidadBancariaDAO;
    
    @RequestMapping(value = {"/EntidadBancaria"})
    public void prueba(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws IOException {
       JsonTransformer jsonTransformer = new JsonTransformerImplJackson();
        
       EntidadBancaria entidadBancaria = entidadBancariaDAO.get(7);
       String jsonEntidad = jsonTransformer.toJson(entidadBancaria);
        
       httpServletResponse.getWriter().println(jsonEntidad);
    }
    
}
