package com.yogayataverma.datastore.repository;

import com.yogayataverma.datastore.model.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repo extends MongoRepository<Model, String> { }
