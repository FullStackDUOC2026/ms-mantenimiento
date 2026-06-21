package com.example.ms_mantenimiento.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoResponse {

    private Long id;
    private Long equipoId;
    private String descripcion;
    private String estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
}