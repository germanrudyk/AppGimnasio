/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.servicios;

import com.example.CuotasGym.entidades.ValorCuota;
import com.example.CuotasGym.excepciones.MiException;
import com.example.CuotasGym.repositorios.ValorCuotaRepositorio;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author German
 */

@Service
public class ValorCuotaServicio {
    
    @Autowired
    private ValorCuotaRepositorio valorCuotaRepositorio;
    
    @Transactional
    public void crearValorCuota(Integer precio) throws MiException{        
        
        if(precio == null){
            throw new MiException("Ingrese un valor de cuota mensual correcto");
        }
        
        ValorCuota valorCuota = new ValorCuota();
        
        valorCuota.setPrecio(precio);
        
        valorCuota.setFechaDesde(LocalDate.now());
        
        valorCuotaRepositorio.save(valorCuota);
        
    }
    
    public Integer tomarUltimoValor(){
        
        List<ValorCuota> cuotas = valorCuotaRepositorio.tomarUltimoValor();
        
        ValorCuota cuota = new ValorCuota();
        
        for (ValorCuota aux : cuotas) {
            cuota = aux;
            break;
        }
        
        return cuota.getPrecio();
        
    }
    
}
