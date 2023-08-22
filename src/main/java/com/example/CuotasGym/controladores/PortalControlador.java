/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.controladores;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Cuota;
import com.example.CuotasGym.enumeraciones.Estado;
import com.example.CuotasGym.excepciones.MiException;
import com.example.CuotasGym.repositorios.CuotaRepositorio;
import com.example.CuotasGym.servicios.ClienteServicio;
import com.example.CuotasGym.servicios.CuotaServicio;
import com.example.CuotasGym.servicios.ValorCuotaServicio;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private CuotaServicio cuotaServicio;

    @Autowired
    private ValorCuotaServicio valorCuotaServicio;

    @GetMapping("/")
    public String index() {

        cuotaServicio.crearNuevasCuotas();

        return "index";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String telefono, @RequestParam String domicilio,
            @RequestParam String inicio,@RequestParam String rutinaEspecial, ModelMap modelo) throws ParseException, MiException, Exception {

        try {
            clienteServicio.crearCliente(nombre, telefono, domicilio, inicio, rutinaEspecial);
        } catch (MiException e) {

            modelo.put("error", e.getMessage());

            return "index";

        }

        return "index";

    }

    @GetMapping("/reporte")
    public String reporte(ModelMap modelo) {

        List<Cuota> cuotas = cuotaServicio.listarCuotasAdeudadas();

        modelo.addAttribute("cuotas", cuotas);

        return "reporte";

    }

    @PostMapping("/valorcuota")
    public String valorCuota(@RequestParam Integer precio, ModelMap modelo) {

        try {
            valorCuotaServicio.crearValorCuota(precio);
        } catch (MiException e) {

            modelo.put("error", e.getMessage());

            return "redirect:";

        }

        return "index";

    }

}
