package com.example.parcial.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class DTOPersona {

    private List<String> dna;
    private boolean mutant;

}
