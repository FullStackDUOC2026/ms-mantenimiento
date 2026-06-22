package com.example.ms_mantenimiento.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MantenimientoResponse {

    private Long id;
    private Long equipoId;
    private String descripcion;
    private String estado;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
}