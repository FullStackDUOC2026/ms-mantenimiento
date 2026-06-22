package com.example.ms_mantenimiento.Controller;
import com.example.ms_mantenimiento.DTO.MantenimientoRequest;
import com.example.ms_mantenimiento.DTO.MantenimientoResponse;
import com.example.ms_mantenimiento.Service.MantenimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    // CREATE
    @PostMapping
    public MantenimientoResponse crear(@RequestBody MantenimientoRequest request){
        return mantenimientoService.crearMantenimiento(request);
    }

    // READ ALL
    @GetMapping
    public List<MantenimientoResponse> listar(){
        return mantenimientoService.listar();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public MantenimientoResponse buscarPorId(@PathVariable Long id){
        return mantenimientoService.buscarPorId(id);
    }


    @PutMapping("/{id}")
    public MantenimientoResponse actualizar(
            @PathVariable Long id,
            @RequestBody MantenimientoRequest request){

        return mantenimientoService.actualizar(id, request);
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        mantenimientoService.eliminar(id);
    }

    @PutMapping("/{id}")
    public MantenimientoResponse actualizarEstado(@PathVariable Long id){
        return mantenimientoService.cambiarEstado(id);
    }
}

