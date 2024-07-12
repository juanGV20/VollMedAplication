package com.med.voll.api.domain.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
) {

    // Este constructor recibe un objeto Medico como parámetro
    // y utiliza sus datos para inicializar los atributos de la clase DatosListadoMedico.
    public DatosListadoMedico(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(),
                medico.getDocumento(),medico.getEmail());
    }
}
