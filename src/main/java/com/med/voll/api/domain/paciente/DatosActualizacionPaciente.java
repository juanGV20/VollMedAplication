package com.med.voll.api.domain.paciente;

import com.med.voll.api.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(@NotNull
                                         Long id,
                                         String nombre,
                                         String telefono,
                                         DatosDireccion direccion) {
}
