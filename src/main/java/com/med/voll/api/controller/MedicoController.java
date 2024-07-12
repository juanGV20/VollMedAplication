package com.med.voll.api.controller;

import com.med.voll.api.domain.direccion.DatosDireccion;
import com.med.voll.api.domain.medico.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRepuestaMedico> registrarMedico(@RequestBody @Valid DatosRegristroMedico datosRegristroMedico,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegristroMedico));
        DatosRepuestaMedico datosRepuestaMedico = new DatosRepuestaMedico(medico.getId(),medico.getNombre(),medico.getEmail(),
                medico.getTelefono(),medico.getEspecialidad().toString(),new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getBarrio(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),
                medico.getDireccion().getComplemento()));
        //Return 201 created
        //URL donde encontrar al medico
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRepuestaMedico);
    }

//    @GetMapping
//    public List<DatosListadoMedico> listarMedicos(){
//        // Map Aplica una transformación a cada médico del flujo
//        // Crea un nuevo objeto DatosListadoMedico para cada médico utilizando el constructor
//        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();
//    }

    @GetMapping // Define la ruta para la petición GET
    public ResponseEntity<Page<DatosListadoMedico>> listarMedicos(@PageableDefault(size = 4) Pageable paginacion) { // Método para listar médicos paginados
        // Obtiene todos los médicos de la base de datos paginados
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
                // .map Transforma cada entidad Medico en un objeto DatosListadoMedico
        //medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    }

    @PutMapping // Indica que este método se ejecutará cuando se reciba una petición HTTP de tipo PUT
    @Transactional // Garantiza la integridad de la base de datos al envolver el método en una transacción
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        // Busca el médico en la base de datos utilizando el ID que se recibe en el objeto datosActualizarMedico
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        // Actualiza los datos del médico con la información que se recibe en el objeto datosActualizarMedico
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRepuestaMedico(medico.getId(),medico.getNombre(),medico.getEmail(),
                medico.getTelefono(),medico.getEspecialidad().toString(),new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getBarrio(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),
                medico.getDireccion().getComplemento())));
    }
//Delete logico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        // Retorna un ResponseEntity con el código de estado 204 No Content
        // para indicar que el médico se eliminó correctamente y no hay contenido que retornar.
        // Se utiliza build() para construir el ResponseEntity completo con la configuración establecida
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosRepuestaMedico> retornaDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRepuestaMedico(medico.getId(),medico.getNombre(),medico.getEmail(),
                medico.getTelefono(),medico.getEspecialidad().toString(),new DatosDireccion(medico.getDireccion().getCalle(),
                medico.getDireccion().getBarrio(),medico.getDireccion().getCiudad(),medico.getDireccion().getNumero(),
                medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico);
    }

//Delete en base de datos
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }
}
