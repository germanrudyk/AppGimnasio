/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.controladores;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.excepciones.MiException;
import com.example.CuotasGym.servicios.ClienteServicio;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author German
 */

@Controller
@RequestMapping("/clientes")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/lista")
    public String clientes(ModelMap modelo) {

        List<Cliente> clientes = clienteServicio.listarClientes();

        modelo.addAttribute("clientes", clientes);

        return "clientes";

    }
    
    @GetMapping("/{id}/baja")
    public String baja(@PathVariable String id){
        
        clienteServicio.baja(id);
        
        return "redirect:/clientes/lista";
        
    }
    
    @GetMapping("/{id}/alta")
    public String alta(@PathVariable String id) throws ParseException{
        
        clienteServicio.alta(id);
        
        return "redirect:/clientes/lista";
        
    }
    
    @PostMapping("/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, @RequestParam String telefono, @RequestParam String domicilio
    , @RequestParam String inicio, @RequestParam String rutinaEspecial, ModelMap modelo) throws MiException{
        
        try {
            clienteServicio.modificar(id, nombre, telefono, domicilio, inicio, rutinaEspecial);
        } catch (MiException e) {
            
            modelo.put("error", e.getMessage());
            return "redirect:/clientes/lista";
            
        }
        
        
        
        return "redirect:/clientes/lista";
        
    }
    
}
