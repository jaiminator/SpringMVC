/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cursohibernate.spring.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UsuarioController {
    
     @RequestMapping(value = {"/Usuario"})
     public void prueba(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().println("Hola Mundo");
    }
    
}
