/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.controladores;

import com.example.CuotasGym.entidades.Cliente;
import com.example.CuotasGym.entidades.Rutina;
import com.example.CuotasGym.servicios.ClienteServicio;
import com.example.CuotasGym.servicios.RutinaServicio;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/rutina")
public class RutinaControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private RutinaServicio rutinaServicio;

    @GetMapping("")
    public String rutina(ModelMap modelo) {

        List<Cliente> clientes = clienteServicio.listarClientesConRutinaEspecial();

        modelo.addAttribute("clientes", clientes);

        return "rutina";

    }

    @PostMapping("/agregar/{id}")
    public String agregar(@PathVariable String id, @RequestParam String detalle, ModelMap modelo) {

        Cliente cliente = clienteServicio.getOne(id);

        rutinaServicio.crearRutina(detalle, cliente);

        return "redirect:../";

    }

    @GetMapping("/agregar/{id}")
    public String agregar(@PathVariable String id, ModelMap modelo) {

        Cliente cliente = clienteServicio.getOne(id);

        modelo.addAttribute("cliente", cliente);

        return "rutina_agregar";

    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable String id, ModelMap modelo) {

        Cliente cliente = clienteServicio.getOne(id);

        List<Rutina> rutinas = rutinaServicio.listarRutinasPorCliente(cliente);
        
        Rutina rutina = new Rutina();
        
        for (Rutina aux : rutinas) {
            rutina = aux;
            break;
        }

        modelo.addAttribute("cliente", cliente);

        modelo.addAttribute("rutinas", rutinas);

      return "rutinas_cliente";

    }
    
    @GetMapping("/enviar/{id}")
    public String enviar(@PathVariable String id){
        
        Rutina rutina = rutinaServicio.getOne(id);
        
        // Enviar rutina
        
        String pythonFilePath = "rutina.py";
        String pythonFileContent = "import requests\n"
                + "import time \n"
                + "import mysql.connector\n"
                + "from datetime import datetime\n"
                + "from datetime import date\n"
                + "\n"
                + "cnx = mysql.connector.connect(\n"
                + "    user='root',\n"
                + "    password='root',\n"
                + "    host='localhost',\n"
                + "    database='cuotasgym'\n"
                + ")\n"
                + "\n"
                + "cursor = cnx.cursor()\n"
                + "\n"
                + "# Obtener todas las rutinas y el numero de telefono de cada cliente\n"
                + "cursor.execute(\"SELECT c.telefono, r.detalle FROM cliente c inner JOIN rutina r ON c.id = r.cliente_id WHERE r.id = '" + rutina.getId() + "';\")\n"
                + "\n"
                + "rows = cursor.fetchall()\n"
                + "\n"
                + "\n"
                + "def sendMessage(para, mensaje):\n"
                + "    url = 'http://localhost:3001/lead'\n"
                + "    \n"
                + "    data = {\n"
                + "        \"message\": mensaje,\n"
                + "        \"phone\": para\n"
                + "    }\n"
                + "    headers = {\n"
                + "        'Content-Type':'application/json'\n"
                + "    }\n"
                + "    print(data)\n"
                + "    response = requests.post(url, json=data, headers=headers)\n"
                + "    time.sleep(10)\n"
                + "    return response\n"
                + "\n"
                + "for row in rows:\n"
                + "\n"
                + "        # Enviar mensaje\n"
                + "        sendMessage(row[0], row[1])\n"
                + "\n"
                + "\n"
                + "cursor.close()\n"
                + "cnx.close()";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pythonFilePath))) {
            System.out.println(rutina.getDetalle());
            writer.write(pythonFileContent);
            writer.flush(); // Asegura que los datos se guarden en el archivo
            System.out.println("Archivo Python creado correctamente.");
            ProcessBuilder processBuilder = new ProcessBuilder("rutina.bat");
            Process process = processBuilder.start();

            // Espera a que el proceso de Python termine
            int exitCode = process.waitFor();
            System.out.println("Código de salida: " + exitCode);

        } catch (IOException ex) {
            Logger.getLogger(RutinaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RutinaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:../";
        
    }
    
}
