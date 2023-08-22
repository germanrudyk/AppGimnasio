/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.entidades;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author German
 */

@Entity
@Table(name = "rutina")
@Getter
@Setter
@NoArgsConstructor
public class Rutina {
    
    @Column
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Column
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate fecha;
    
    @Column
    private String detalle;
    
    @OneToOne
    private Cliente cliente;
    
    @Column
    private String mostrarFecha;
}
