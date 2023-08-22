/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.repositorios;

import com.example.CuotasGym.entidades.ValorCuota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */

@Repository
public interface ValorCuotaRepositorio extends JpaRepository<ValorCuota, String>{
    
    @Query("SELECT v FROM ValorCuota v ORDER BY v DESC")
    public List<ValorCuota> tomarUltimoValor();
    
}
