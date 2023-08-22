/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.servicios;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Cuota;
import com.example.CuotasGym.enumeraciones.Estado;
import com.example.CuotasGym.repositorios.CuotaRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */
@Service
public class CuotaServicio {

    @Autowired
    private CuotaRepositorio cuotaRepositorio;
    
    @Autowired
    private ValorCuotaServicio valorCuotaServicio;

    public void crearCuotaInicial(String inicio, Cliente cliente) throws ParseException {

        Cuota cuota = new Cuota();

        establecerFechas(cuota, inicio);

        cuota.setCliente(cliente);

        cuota.setEstado(Estado.ADEUDADA);

        cuota.setNotificada(Boolean.FALSE);

        cuota.setVencida(Boolean.FALSE);

        cuota.setProximaAgregada(Boolean.FALSE);
        
        cuota.setPrecio(valorCuotaServicio.tomarUltimoValor());

        cuotaRepositorio.save(cuota);

    }
    
    public void establecerFechas(Cuota cuota, String inicio){
        
        // Define el patrón de formato de la fecha
        String patron = "yyyy-MM-dd";

        // Crea un objeto DateTimeFormatter con el patrón de formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);

        // Convierte la cadena de texto a LocalDate utilizando el formatter
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);

        cuota.setInicio(fechaInicio);

        // Genera la fecha de vencimiento a partir de la fecha inicial
        cuota.setVencimiento(fechaInicio.plusMonths(1).minusDays(1));
        
        // Redefinir el patron de formato de fecha
        patron = "dd-MM-yyyy";
        
        formatter = DateTimeFormatter.ofPattern(patron);
        
        cuota.setMostrarInicio(fechaInicio.format(formatter));
        
        cuota.setMostrarVencimiento(cuota.getVencimiento().format(formatter));
        
    }

    public void crearNuevasCuotas() {

        List<Cuota> cuotas = listarCuotas();

        for (Cuota aux : cuotas) {

            int comparacion = LocalDate.now().compareTo(aux.getVencimiento());
            
            System.out.println(comparacion);

            if (comparacion > 0) {

                if (!aux.getProximaAgregada()) {

                    Cuota cuota = new Cuota();

                    establecerFechas(cuota, aux.getVencimiento().plusDays(1).toString());

                    cuota.setCliente(aux.getCliente());

                    cuota.setEstado(Estado.ADEUDADA);

                    cuota.setNotificada(Boolean.FALSE);

                    cuota.setVencida(Boolean.FALSE);

                    cuota.setProximaAgregada(Boolean.FALSE);
                    
                    cuota.setPrecio(valorCuotaServicio.tomarUltimoValor());

                    // Commit de la nueva cuota
                    cuotaRepositorio.save(cuota);

                    aux.setProximaAgregada(Boolean.TRUE);

                    // Commit de la cuota verificada
                    cuotaRepositorio.save(aux);

                }

            }

        }

    }

    public List<Cuota> listarCuotas() {

        return cuotaRepositorio.listarCuotas();

    }

    public List<Cuota> cuotasCliente(Cliente cliente) {

        return cuotaRepositorio.listarEnFormaDescendente(cliente);

    }

    public List<Cuota> listarPrimerasDiezCuotas(Cliente cliente) {

        List<Cuota> cuotas = cuotaRepositorio.listarEnFormaDescendente(cliente);

        List<Cuota> cuotasAMostrar = new ArrayList();

        int contador = 0;

        for (Cuota aux : cuotas) {
            if (contador < 10) {
                cuotasAMostrar.add(aux);
                contador++;
            } else {
                break;
            }
        }

        return cuotasAMostrar;

    }

    public List<Cuota> CuotasAdeudadas() {

        List<Cuota> cuotasAVerificar = cuotaRepositorio.listarCuotasVencidasAdeudadas();

        List<Cuota> cuotasAdeudadas = new ArrayList();

        for (Cuota aux : cuotasAVerificar) {

            if ("ADEUDADA".equals(aux.getEstado().toString())) {

                cuotasAdeudadas.add(aux);
            }
        }

        return cuotasAdeudadas;

    }
    
    public List<Cuota> listarCuotasAdeudadas(){
        
        return cuotaRepositorio.listarCuotasActualesAdeudadas();
        
    }
    
    public Cuota getOne(String id){
        
        return cuotaRepositorio.getOne(id);
        
    }
    
    public void cambiarEstado(String id){
        
        Cuota cuota = getOne(id);
        
        if("ADEUDADA".equals(cuota.getEstado().toString())){
            cuota.setEstado(Estado.CANCELADA);
        } else {
            cuota.setEstado(Estado.ADEUDADA);
        }

        cuotaRepositorio.save(cuota);
        
    }

}
