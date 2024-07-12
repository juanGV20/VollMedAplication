package com.med.voll.api.domain.consulta;

import com.med.voll.api.domain.medico.Medico;
import com.med.voll.api.domain.paciente.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas") // Indica que la clase Medico se mapea a la tabla "medicos" en la base de datos.
@Entity(name = "Consulta") // Indica que la clase Medico es una entidad JPA, que se puede persistir en la base de datos.
@Getter // Genera automáticamente métodos getter para todos los atributos.
@NoArgsConstructor // Genera un constructor sin argumentos (constructor por defecto).
@AllArgsConstructor // Genera un constructor que recibe como argumentos todos los atributos.
@EqualsAndHashCode(of = "id")// Genera los métodos equals() y hashCode() para la clase,
// pero solo considerando el atributo id para la comparación.
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

}
