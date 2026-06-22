package com.example.ms_mantenimiento.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientoRequest {

    private Long equipoId;
    private String descripcion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;

}