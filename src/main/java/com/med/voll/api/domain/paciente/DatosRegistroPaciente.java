package com.med.voll.api.domain.paciente;

import com.med.voll.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DatosRegistroPaciente(

        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotBlank
        @Size(min = 0, max = 15)
        String telefono,
        @NotNull
        @Valid
        DatosDireccion direccion

) {
}
