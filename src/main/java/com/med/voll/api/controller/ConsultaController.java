package com.med.voll.api.controller;

import com.med.voll.api.domain.consulta.AgendaDeConsultaService;
import com.med.voll.api.domain.consulta.DatosAgendarConsulta;
import com.med.voll.api.domain.consulta.DatosDetallesConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        consultaService.agendar(datos);
        return ResponseEntity.ok(new DatosDetallesConsulta(null,null,null,null));

    }

}
