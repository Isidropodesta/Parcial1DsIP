package com.example.parcial.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Persona")
public class Persona extends Base {

    @Column(name = "dna")
    private List<String> dna;

    @Column(name = "isMutant")
    private boolean mutant;



}
