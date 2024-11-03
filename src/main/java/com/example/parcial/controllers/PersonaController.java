package com.example.parcial.controllers;

import com.example.parcial.entities.Persona;
import com.example.parcial.services.PersonaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.parcial.DTOs.DTOPersona;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/persona")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{

    //este metodo va a recibir una persona y dice si es un mutante o no
    @PostMapping("/mutant")
    public ResponseEntity<?> examinePerson(@RequestBody DTOPersona dtoPersona) {

        List<String> dna_person = dtoPersona.getDna();
        Persona persona = Persona.builder()
                .dna(dna_person)
                .build();

        try {
            boolean is_mutant = service.isMutant(dtoPersona.getDna());
            if(is_mutant){
                persona.setMutant(true);
                service.save(persona);
                dtoPersona.setMutant(true);
                return ResponseEntity.status(HttpStatus.OK).body(dtoPersona);
            }else{
                persona.setMutant(false);
                service.save(persona);
                dtoPersona.setMutant(false);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dtoPersona);
            }

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getStats());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
