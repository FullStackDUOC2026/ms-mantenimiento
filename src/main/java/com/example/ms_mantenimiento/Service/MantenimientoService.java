package com.example.ms_mantenimiento.Service;

import com.example.ms_mantenimiento.Client.EquiposClient;
import com.example.ms_mantenimiento.DTO.EquiposDTO;
import com.example.ms_mantenimiento.DTO.MantenimientoRequest;
import com.example.ms_mantenimiento.DTO.MantenimientoResponse;
import com.example.ms_mantenimiento.Entity.Mantenimiento;
import com.example.ms_mantenimiento.Repository.MantenimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;

    private final EquiposClient equiposClient;
    public MantenimientoResponse crearMantenimiento(MantenimientoRequest request){

        EquiposDTO equipo = equiposClient.getEquipoById(request.getEquipoId());

        if (equipo == null) {
            throw new RuntimeException("Equipo no existe");
        }

        if (!equipo.getEstado().equals("LIBRE")) {
            throw new RuntimeException("Equipo no disponible");
        }

        Mantenimiento mantenimiento = new Mantenimiento();

        mantenimiento.setEquipoId(request.getEquipoId());
        mantenimiento.setDescripcion(request.getDescripcion());
        mantenimiento.setEstado("activo");
        mantenimiento.setFechaIngreso(request.getFechaIngreso());
        mantenimiento.setFechaSalida(request.getFechaSalida());

        Mantenimiento saved = mantenimientoRepository.save(mantenimiento);

        equiposClient.marcarMantencion(request.getEquipoId());

        return mapToResponse(saved);
    }
    public List<MantenimientoResponse> listar(){

        return mantenimientoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    public MantenimientoResponse buscarPorId(Long id){

        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        return mapToResponse(mantenimiento);
    }


    public MantenimientoResponse actualizar(Long id, MantenimientoRequest request){

        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        mantenimiento.setEquipoId(request.getEquipoId());
        mantenimiento.setDescripcion(request.getDescripcion());
        mantenimiento.setFechaIngreso(request.getFechaIngreso());
        mantenimiento.setFechaSalida(request.getFechaSalida());

        Mantenimiento updated = mantenimientoRepository.save(mantenimiento);

        return mapToResponse(updated);
    }


    public void eliminar(Long id){
        mantenimientoRepository.deleteById(id);
    }

    private MantenimientoResponse mapToResponse(Mantenimiento m){

        MantenimientoResponse response = new MantenimientoResponse();

        response.setId(m.getId());
        response.setEquipoId(m.getEquipoId());
        response.setDescripcion(m.getDescripcion());
        response.setEstado(m.getEstado());
        response.setFechaIngreso(m.getFechaIngreso());
        response.setFechaSalida(m.getFechaSalida());

        return response;
    }

    public MantenimientoResponse cambiarEstado(Long id){

        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("mantenimiento no encontrado"));

        mantenimiento.setEstado("finalizado");

        mantenimientoRepository.save(mantenimiento);

        return MantenimientoResponse.builder()
                .equipoId(mantenimiento.getEquipoId())
                .descripcion(mantenimiento.getDescripcion())
                .estado(mantenimiento.getEstado())
                .fechaIngreso(mantenimiento.getFechaIngreso())
                .fechaSalida(mantenimiento.getFechaSalida())
                .build();
    }
}