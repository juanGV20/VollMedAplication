package com.med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

//Usamos validaciones para los datos
public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String barrio,
        @NotBlank
        String ciudad,
        @NotBlank
        String numero,
        @NotBlank
        String complemento) {
}
