package com.med.voll.api.domain.medico;


import com.med.voll.api.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "medicos") // Indica que la clase Medico se mapea a la tabla "medicos" en la base de datos.
@Entity(name = "Medico") // Indica que la clase Medico es una entidad JPA, que se puede persistir en la base de datos.
@Getter // Genera automáticamente métodos getter para todos los atributos.
@NoArgsConstructor // Genera un constructor sin argumentos (constructor por defecto).
@AllArgsConstructor // Genera un constructor que recibe como argumentos todos los atributos.
@EqualsAndHashCode(of = "id")// Genera los métodos equals() y hashCode() para la clase,
// pero solo considerando el atributo id para la comparación.
public class Medico {

    @Id // Indica que el atributo id es la clave principal de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor del atributo id se genera automáticamente por la base de datos.
    private Long id;

    private String nombre; // Atributo para el nombre del médico.
    private String email; // Atributo para el correo electrónico del médico.
    private String telefono;
    private String documento; // Atributo para el documento de identidad del médico.
    private Boolean activo;

    @Enumerated(EnumType.STRING) // Indica que el atributo especialidad es un atributo enumerado y
    // se almacena como una cadena de texto en la base de datos.
    private Especialidad especialidad; // Atributo para la especialidad del médico.

    @Embedded // Indica que el atributo direccion es un atributo embebido, que se almacena como parte de la entidad.
    private Direccion direccion; // Atributo para la dirección del médico.

    public Medico(DatosRegristroMedico datosRegristroMedico) {
        this.nombre = datosRegristroMedico.nombre();
        this.email = datosRegristroMedico.email();
        this.telefono = datosRegristroMedico.telefono();
        this.documento = datosRegristroMedico.documento();
        this.activo = true;
        this.especialidad = datosRegristroMedico.especialidad();
        this.direccion = new Direccion(datosRegristroMedico.direccion());
    }

    public void desactivarMedico() {
        this.activo = false;
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        //Valida que nombre no es vacio para actualizar los datos
        if(datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.telefono() != null){
            this.telefono = datosActualizarMedico.telefono();
        }
        if(datosActualizarMedico.datosDireccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.datosDireccion());
        }
    }
}
