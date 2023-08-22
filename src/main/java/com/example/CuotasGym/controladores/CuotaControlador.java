/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.controladores;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Cuota;
import com.example.CuotasGym.servicios.ClienteServicio;
import com.example.CuotasGym.servicios.CuotaServicio;
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
@RequestMapping("/cuotas")
public class CuotaControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private CuotaServicio cuotaServicio;

    @GetMapping("/{id}")
    public String cuotas(@PathVariable String id, ModelMap modelo) {

        Cliente cliente = clienteServicio.getOne(id);

        List<Cuota> cuotas = cuotaServicio.listarPrimerasDiezCuotas(cliente);

        modelo.addAttribute("cliente", cliente);

        modelo.addAttribute("cuotas", cuotas);

        return "cuotas.html";

    }

    @PostMapping("/{id}/cambiar")
    public String cambiarEstado(@PathVariable String id, @RequestParam String idCuota) {

        cuotaServicio.cambiarEstado(idCuota);

        return "redirect:/cuotas/{id}";

    }

}
