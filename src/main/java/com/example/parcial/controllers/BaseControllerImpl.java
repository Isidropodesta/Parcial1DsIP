package com.example.parcial.controllers;

import com.example.parcial.entities.Base;
import com.example.parcial.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S service;

    @Override
    public ResponseEntity<?> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        return null;
    }

    //metodo que recibe un json de Persona y devuelve si es o no mutante
    @Override
    public ResponseEntity<?> save(E entity) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(Long id, E entity) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }



}
