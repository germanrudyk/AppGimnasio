/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CuotasGym.entidades;

import com.example.CuotasGym.enumeraciones.Estado;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "cuota")
@Getter
@Setter
@NoArgsConstructor
public class Cuota {

    @Column
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate inicio;

    @Column
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate vencimiento;
    
    @Column
    private Integer precio;

    @OneToOne
    private Cliente cliente;

    @Column
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column
    private Boolean vencida;
    
    @Column
    private Boolean notificada;
    
    @Column
    private Boolean proximaAgregada;
    
    @Column
    private String mostrarInicio;
    
    @Column
    private String mostrarVencimiento; 

}
