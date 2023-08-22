/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.servicios;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.excepciones.MiException;
import com.example.CuotasGym.repositorios.ClienteRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */
@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CuotaServicio cuotaServicio;

    @Transactional
    public void crearCliente(String nombre, String telefono, String domicilio, String inicio, String rutinaEspecial) throws ParseException, MiException {

        validar(nombre, telefono, domicilio, inicio, rutinaEspecial);
        
        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setTelefono("549" + telefono);

        cliente.setDomicilio(domicilio);

        // Define el patrón de formato de la fecha
        String patron = "yyyy-MM-dd";

        // Crea un objeto DateTimeFormatter con el patrón de formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);

        // Convierte la cadena de texto a LocalDate utilizando el formatter
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);

        cliente.setFechaInicio(fechaInicio);

        cliente.setActivo(Boolean.TRUE);

        if ("true".equals(rutinaEspecial)) {

            cliente.setRutinaEspecial(true);

        } else {

            cliente.setRutinaEspecial(false);
        }

        clienteRepositorio.save(cliente);

        cuotaServicio.crearCuotaInicial(inicio, cliente);

    }

    public List<Cliente> listarClientes() {

        return clienteRepositorio.findAll();

    }
    
    public List<Cliente> listarClientesConRutinaEspecial(){
        
        return clienteRepositorio.listarClientesConRutinaEspecial();
        
    }

    public Cliente getOne(String id) {

        return clienteRepositorio.getOne(id);

    }

    public void baja(String id) {

        Cliente cliente = getOne(id);

        cliente.setActivo(Boolean.FALSE);

        clienteRepositorio.save(cliente);

    }

    public void alta(String id) throws ParseException {

        Cliente cliente = getOne(id);

        cliente.setActivo(Boolean.TRUE);
        
        cuotaServicio.crearCuotaInicial(cliente.getFechaInicio().toString(), cliente);

        clienteRepositorio.save(cliente);

    }

    public void modificar(String id, String nombre, String telefono, String domicilio, String inicio, String rutinaEspecial) throws MiException {
              
        Cliente cliente = getOne(id);
        
        validar(nombre, telefono, domicilio, inicio, rutinaEspecial);
        
        cliente.setNombre(nombre);

        cliente.setTelefono("549"+telefono);

        cliente.setDomicilio(domicilio);
        
        // Define el patrón de formato de la fecha
        String patron = "yyyy-MM-dd";

        // Crea un objeto DateTimeFormatter con el patrón de formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);

        // Convierte la cadena de texto a LocalDate utilizando el formatter
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);

        cliente.setFechaInicio(fechaInicio);

        if ("true".equals(rutinaEspecial)) {

            cliente.setRutinaEspecial(true);

        } else {

            cliente.setRutinaEspecial(false);
        }

        clienteRepositorio.save(cliente);

    }
    
    private void validar(String nombre, String telefono, String domicilio, String inicio, String rutinaEspecial) throws MiException{
        
        if(nombre.isEmpty() || nombre == null){
            throw new MiException("Debe ingresar un nombre");
        }
        
        if(telefono.isEmpty() || telefono == null){
            throw new MiException("Debe ingresar un numero de telefono válido");
        }
        
        if(domicilio.isEmpty() || domicilio == null){
            throw new MiException("Debe ingresar un domicilio");
        }
        
        if(inicio.isEmpty() || inicio == null){
            throw new MiException("Selecciona fecha de inicio");
        }
        
        if(rutinaEspecial.isEmpty() || rutinaEspecial == null){
            throw new MiException("Eliga si el cliente tiene rutina especial");
        }
        
    }

}
