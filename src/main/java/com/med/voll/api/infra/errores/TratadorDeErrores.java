package com.med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    // Maneja la excepción EntityNotFoundException, que se lanza cuando no se encuentra un recurso.
    // Retorna un ResponseEntity con un código de estado 404 (Not Found).
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    // Maneja la excepción MethodArgumentNotValidException, que se lanza cuando los argumentos de un método no son válidos.
    // Retorna un ResponseEntity con un código de estado 400 (Bad Request) y un cuerpo que contiene una lista de errores.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        // Crea una lista de errores a partir de los errores de campo.
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        // Retorna un ResponseEntity con un código de estado 400 (Bad Request) y un cuerpo que contiene la lista de errores.
        return ResponseEntity.badRequest().body(errores);
    }

    // Define un record para representar un error de validación.
    // El record tiene dos campos: campo (el nombre del campo que causó el error) y error (el mensaje de error).
    private record DatosErrorValidacion(String campo, String error){
        // Constructor que recibe un FieldError y crea un nuevo DatosErrorValidacion.
        public DatosErrorValidacion(FieldError error){
            // Asigna el nombre del campo y el mensaje de error del FieldError.
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
