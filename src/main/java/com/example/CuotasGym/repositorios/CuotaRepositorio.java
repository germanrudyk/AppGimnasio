/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.repositorios;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Cuota;
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
public interface CuotaRepositorio extends JpaRepository<Cuota, String> {

    @Query("SELECT c FROM Cuota c WHERE c.cliente.activo = true")
    public List<Cuota> listarCuotas();

    @Query("SELECT c FROM Cuota c WHERE c.cliente = :cliente ORDER BY c.inicio DESC")
    public List<Cuota> listarEnFormaDescendente(@Param("cliente") Cliente cliente);

    @Query("SELECT c FROM Cuota c WHERE c.vencida = true AND c.estado = 'ADEUDADA' AND c.cliente.activo = true ORDER BY c.vencimiento DESC")
    public List<Cuota> listarCuotasVencidasAdeudadas();

    @Query("SELECT c FROM Cuota c WHERE c.vencida = false AND c.estado = 'ADEUDADA' AND c.cliente.activo = true ORDER BY c.vencimiento DESC")
    public List<Cuota> listarCuotasActualesAdeudadas();

}
