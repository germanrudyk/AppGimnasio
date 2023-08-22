/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.servicios;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Rutina;
import com.example.CuotasGym.repositorios.RutinaRepositorio;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */

@Service
public class RutinaServicio {
    
    @Autowired
    private RutinaRepositorio rutinaRepositorio;
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @Transactional
    public void crearRutina(String detalle, Cliente cliente){
        
        Rutina rutina = new Rutina();
        
        rutina.setDetalle(detalle);
        
        detalle = detalle.replaceAll("\n", "\n");
        
        rutina.setCliente(cliente);
        
        rutina.setFecha(LocalDate.now());
        
        mostrarFecha(rutina);
        
        rutinaRepositorio.save(rutina);       
        
    }
    
    private void mostrarFecha(Rutina rutina){
        
        // Definir el patron de formato de fecha
        String patron = "dd-MM-yyyy";
        
        // Crea un objeto DateTimeFormatter con el patrón de formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
        
        rutina.setMostrarFecha(rutina.getFecha().format(formatter));
        
    }
    
    public List<Rutina> listarRutinasPorCliente(Cliente cliente){
        
        return rutinaRepositorio.listarRutinasPorCliente(cliente);
        
    }
    
    public Rutina getOne(String id){
        
        return rutinaRepositorio.getOne(id);
        
    }
    
    
    
}
