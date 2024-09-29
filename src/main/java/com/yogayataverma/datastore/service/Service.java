package com.yogayataverma.datastore.service;

import com.yogayataverma.datastore.model.Model;
import com.yogayataverma.datastore.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repo repository;

    public List<Model> findAll() {
        return repository.findAll();
    }

    public Model findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Model save(Model product) {
        return repository.save(product);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
