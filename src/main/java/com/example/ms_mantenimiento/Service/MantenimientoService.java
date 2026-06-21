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
        mantenimiento.setEstado(request.getEstado());
        mantenimiento.setFechaIngreso(request.getFechaIngreso());
        mantenimiento.setFechaSalida(request.getFechaSalida());

        Mantenimiento saved = mantenimientoRepository.save(mantenimiento);

        // 🔥 AQUÍ ESTÁ EL CAMBIO
        equiposClient.marcarMantencion(request.getEquipoId());

        return mapToResponse(saved);
    }
    // LISTAR TODOS
    public List<MantenimientoResponse> listar(){

        return mantenimientoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public MantenimientoResponse buscarPorId(Long id){

        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        return mapToResponse(mantenimiento);
    }

    // ACTUALIZAR
    public MantenimientoResponse actualizar(Long id, MantenimientoRequest request){

        Mantenimiento mantenimiento = mantenimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        mantenimiento.setEquipoId(request.getEquipoId());
        mantenimiento.setDescripcion(request.getDescripcion());
        mantenimiento.setEstado(request.getEstado());
        mantenimiento.setFechaIngreso(request.getFechaIngreso());
        mantenimiento.setFechaSalida(request.getFechaSalida());

        Mantenimiento updated = mantenimientoRepository.save(mantenimiento);

        return mapToResponse(updated);
    }

    // ELIMINAR
    public void eliminar(Long id){
        mantenimientoRepository.deleteById(id);
    }

    // MAPPER (Entity → DTO)
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
}