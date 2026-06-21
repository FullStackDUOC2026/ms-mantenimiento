package com.example.ms_mantenimiento.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mantenimientos")
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long equipoId;
    @Column(name = "descripcion",nullable = false, length = 100)
    private String descripcion;
    @Column(name = "estado",nullable = false, length = 100)
    private String estado;
    @Column(name = "fecha_ingreso",nullable = false)
    private LocalDate fechaIngreso;
    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

}
