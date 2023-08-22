/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.repositorios;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Rutina;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */

@Repository
public interface RutinaRepositorio extends JpaRepository<Rutina, String>{
    
    @Query("SELECT r FROM Rutina r WHERE r.cliente = :cliente ORDER BY r DESC")
    public List<Rutina> listarRutinasPorCliente(@Param("cliente") Cliente cliente);
    
}
