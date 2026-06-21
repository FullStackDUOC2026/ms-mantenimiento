package com.example.ms_mantenimiento.Repository;

import com.example.ms_mantenimiento.Entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento,Long> {
}
