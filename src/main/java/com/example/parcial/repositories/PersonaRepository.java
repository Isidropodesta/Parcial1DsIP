package com.example.parcial.repositories;

import com.example.parcial.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {


    //query que me busca la cantidad de mutantes
    @Query("SELECT COUNT(m) FROM Persona m WHERE m.mutant = true" )
    Integer findQuantityOfMutants();

    //query que me usca la cantidad de humanos
    @Query("SELECT COUNT(m) FROM Persona m WHERE m.mutant = false" )
    Integer findQuantityOfHumans();


}
