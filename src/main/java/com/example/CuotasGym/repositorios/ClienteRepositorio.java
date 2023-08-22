/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.repositorios;

import com.example.CuotasGym.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author German
 */

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
    @Query("SELECT c FROM Cliente c WHERE c.rutinaEspecial = true")
    public List<Cliente> listarClientesConRutinaEspecial();
    
}
