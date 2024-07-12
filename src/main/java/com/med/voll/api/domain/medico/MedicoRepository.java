package com.med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT m from Medico m 
            WHERE m.activo = 1 AND 
            m.especialidad = :especialidad and 
            m.id not in(
            SELECT c.medico.id from Consulta c
            c.data = :fecha
            ) ORDER BY rand() 
            limit 1
            """)
    Medico SeleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
}
