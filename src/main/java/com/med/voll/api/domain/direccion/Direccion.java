package com.med.voll.api.domain.direccion;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Genera automáticamente métodos getter para todos los atributos.
@NoArgsConstructor // Genera un constructor sin argumentos (constructor por defecto).
@AllArgsConstructor // Genera un constructor que recibe como argumentos todos los atributos.
@Embeddable // Indica que la clase Direccion es una clase embebible, que se puede incluir como parte de otra entidad.
public class Direccion {
    private String calle;
    private String barrio;
    private String ciudad;
    private String numero;
    private String complemento;

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.barrio = direccion.barrio();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
    }

    public Direccion actualizarDatos(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.barrio = datosDireccion.barrio();
        this.ciudad = datosDireccion.ciudad();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        return this;
    }
}
